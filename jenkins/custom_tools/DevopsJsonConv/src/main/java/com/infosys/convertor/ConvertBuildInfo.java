/***********************************************************************************************
*
* Copyright 2018 Infosys Ltd.
* Use of this source code is governed by MIT license that can be found in the LICENSE file or at
* https://opensource.org/licenses/MIT.
*
***********************************************************************************************/
package com.infosys.convertor;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;

import org.apache.log4j.Logger;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import com.infosys.json.Accleratest;
import com.infosys.json.BuildDetail;
import com.infosys.json.BuildOwner;
import com.infosys.json.Checkmarx;
import com.infosys.json.Codecoverage;
import com.infosys.json.Functional;
import com.infosys.json.Jacoco;
import com.infosys.json.JsonClass;
import com.infosys.json.Qualitia;
import com.infosys.json.RobotJson;
import com.infosys.json.SecurityTest;
import com.infosys.json.Selenium;
import com.infosys.jsonschema.VersionInfo;
import com.infosys.utilities.changeset.ChangeSet;
import com.infosys.utilities.changeset.ChangeSet.Culprits;
import com.infosys.utilities.changeset.ChangeSet.Item;
import com.infosys.utilities.changeset.CodeCoverage;
import com.infosys.utilities.changeset.JobDetails;
import com.infosys.utilities.checkmarx.CxXMLResults;
import com.infosys.utilities.itops.Itops;
import com.infosys.utilities.itops.Itops.Suite.Test.Class;
import com.infosys.utilities.itops.Itops.Suite.Test.Class.TestMethod;
import com.infosys.utilities.qualitia.QualitiaTest;
import com.infosys.utilities.qualitia.QualitiaTest.Suite;
import com.infosys.utilities.qualitia.QualitiaTest.Suite.TCs;
import com.infosys.utilities.qualitia.QualitiaTest.Suite.TCs.TC;
import com.infosys.utilities.robot.Robot;
import com.infosys.utilities.robot.Stat;
import com.infosys.utilities.robot.Statistics;
import com.infosys.utilities.robot.Stats;
import com.infosys.utilities.seleniumtestng.Testng;

/**
 * The class ConvertBuildInfo has methods for parsing build details
 * 
 * @author shivam.bhagat
 *
 */
public class ConvertBuildInfo {
	private static final Logger logger = Logger.getLogger(ConvertBuildInfo.class);

	private static SecurityTest securityTest = new SecurityTest();
	private static Codecoverage codeCoverage = new Codecoverage();
	public static final String NAMESPACECLASSMAP = "/NamespaceClassMap.csv";
	public static final String ERROR = "Error: ";
	private static String buildTime = null;
	private static String buildstatus = null;
	private static String timestamp = null;
	private static String stageName = null;
	private static final String SRC = "/src/";
	private static final String CONEVERSIONERROR = "Conversion error for ";

	private ConvertBuildInfo() {
	}

	/**
	 * 
	 * @param inputPath
	 * @param jsonClass
	 * @param prefixForId
	 * @param tfsPath
	 * @return
	 */
	public static JsonClass convert(String inputPath, JsonClass jsonClass, String prefixForId, String tfsPath) {
		try {
			EditDocType.edit(inputPath);
			File file = new File(inputPath);
			JAXBContext jaxbContext = JAXBContext.newInstance(ChangeSet.class);

			Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
			ChangeSet c = (ChangeSet) jaxbUnmarshaller.unmarshal(file);
			List<VersionInfo> listInfo = null;
			if (c.getDuration() != null) {
				buildTime = c.getDuration();
			}
			// setting time stamp value in JSON ..
			if (c.getTimestamp() != null) {
				timestamp = c.getTimestamp();
			}

			if (c.getAppName() != null) {
				stageName = c.getAppName();
			}

			if (c.getBuildStatus() != null) {
				buildstatus = c.getBuildStatus();
			}
			if (c.getCulprits() != null) {
				List<BuildOwner> listB = iterateCulprits(c);
				jsonClass.setBuildOwners(listB);
			}
			if (c.getItem() != null) {
				List<ChangeSet.Item> item = c.getItem();
				HashMap<String, String> nsClassMapForDotNet = mapNsClass(inputPath);
				listInfo = iterateItem(item, prefixForId, nsClassMapForDotNet, tfsPath, c);
			}
			if (jsonClass.getBuildDetails().get(0) != null) {
				jsonClass.getBuildDetails().get(0).setBuildTime(buildTime);
				jsonClass.getBuildDetails().get(0).setTimestamp(timestamp);
				jsonClass.getBuildDetails().get(0).setBuiltStatus(buildstatus);

			}
			jsonClass.setVersionInfo(listInfo);

			logger.info("Report Converted Successfully..!!");
			return jsonClass;

		} catch (Exception e) {
			logger.error(CONEVERSIONERROR + inputPath + ERROR + e);
		}
		return jsonClass;

	}

	/**
	 * returns list of buildowners for all culprits
	 * 
	 * @param c
	 * @return
	 */
	private static List<BuildOwner> iterateCulprits(ChangeSet c) {
		List<BuildOwner> listB = new ArrayList<>();
		for (Culprits m : c.getCulprits()) {
			BuildOwner b = getBuildOwnerObject();
			b.setId(m.getName());
			listB.add(b);
		}
		if (listB.isEmpty() || listB.get(0).getId() == null)
			listB = null;
		return listB;
	}

	private static BuildOwner getBuildOwnerObject() {
		return new BuildOwner();
	}

	/**
	 * returns list of versioninfo for all items
	 * 
	 * @param item
	 * @param prefixForId
	 * @param nsClassMapForDotNet
	 * @param tfsPath
	 * @param c
	 * @return
	 */
	private static List<VersionInfo> iterateItem(List<ChangeSet.Item> item, String prefixForId,
			HashMap<String, String> nsClassMapForDotNet, String tfsPath, ChangeSet c) {

		List<VersionInfo> listInfo = new ArrayList<>();
		DateFormat dateFormatter = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");

		for (ChangeSet.Item i : item) {

			if (i.getAffectedPath() == null)
				continue;

			List<String> clas = i.getAffectedPath();
			for (String cls : clas) {
				VersionInfo bi = getVersionInfoObject();
				if (i.getTimestamp() != null) {
					Date date = getDate(i.getTimestamp());
					bi.setLastModified(dateFormatter.format(date));
				}

				setCommitMsgFunc(i, bi);

				if (bi.getCommitMessage().equals("")) {
					bi.setCommitMessage("File Checked In");
				}

				setCommitIdFunc(i, bi);
				setUserFunc(i, bi);
				setVersionFunc(i, bi);
				setRemoteUrlFunc(c, bi);

				if (cls != null)
					bi = setVersionInfoId(cls, bi, prefixForId, nsClassMapForDotNet, tfsPath);

				if (!bi.getid().equals("none"))
					listInfo.add(bi);
			}
		}

		if (listInfo.isEmpty()) {
			return listInfo;
		}

		return listInfo;
	}

	/**
	 * 
	 * @param c
	 * @param bi
	 */
	private static void setRemoteUrlFunc(ChangeSet c, VersionInfo bi) {

		if (c.getScmurl() != null) {
			bi.setScmurl(c.getScmurl());
			logger.info("SCM URL added");

		}

	}

	private static Date getDate(Long timestamp) {
		return new Date(timestamp);
	}

	private static VersionInfo getVersionInfoObject() {
		return new VersionInfo();
	}

	private static void setCommitMsgFunc(Item i, VersionInfo bi) {
		if (i.getComment() != null) {
			bi.setCommitMessage(i.getComment());
		}
	}

	private static void setCommitIdFunc(Item i, VersionInfo bi) {
		if (i.getCommitId() != null) {
			bi.setCommitId(i.getCommitId());
		}
	}

	private static void setUserFunc(Item i, VersionInfo bi) {

		if (i.getAuthor().getFullName() != null) {
			bi.setLastModifiedBy(i.getAuthor().getFullName());
		}
	}

	private static void setVersionFunc(Item i, VersionInfo bi) {
		if (i.getCommitId() != null)
			bi.setLatestFileVersion(i.getCommitId());
	}

	/**
	 * 
	 * @param cls
	 * @param bi
	 * @param prefixForId
	 * @param nsClassMapForDotNet
	 * @param tfsPath
	 * @return
	 */
	private static VersionInfo setVersionInfoId(String cls, VersionInfo bi, String prefixForId,
			HashMap<String, String> nsClassMapForDotNet, String tfsPath) {
		if (cls.endsWith(".java")) {
			if (cls.lastIndexOf(SRC) == -1)
				return bi;
			cls = cls.substring(cls.lastIndexOf(SRC));
			String name;
			if (cls.contains("src/main/java/"))
				name = cls.split("src.main.java.")[1].replace("/", "_");
			else if (cls.contains("src/test/java/"))
				name = cls.split("src.test.java.")[1].replace("/", "_");
			else
				name = cls.split("src.")[1].replace("/", "_");
			bi.setid(prefixForId + name.replace(".", "_"));
		} else if (cls.endsWith(".cs")) {
			if (cls.startsWith("$")) {
				cls = cls.split("\\" + tfsPath + "/")[1];
			}
			String keyToCheck = cls.replace("/", "\\");
			String[] fileNameSplitArr = cls.split("/");
			if (nsClassMapForDotNet != null && nsClassMapForDotNet.containsKey(keyToCheck)
					&& !nsClassMapForDotNet.get(keyToCheck).equals(""))
				bi.setid(prefixForId + nsClassMapForDotNet.get(keyToCheck));
			else if (nsClassMapForDotNet != null && nsClassMapForDotNet.containsKey(keyToCheck))
				bi.setid(prefixForId + fileNameSplitArr[fileNameSplitArr.length - 1].replace(".", "_"));
			else
				bi.setid(prefixForId + "DefaultPackage_"
						+ fileNameSplitArr[fileNameSplitArr.length - 1].replace(".", "_"));
		} else {
			if (cls.startsWith("$")) {
				cls = cls.split("\\" + tfsPath + "/")[1];
			} else if (cls.lastIndexOf(SRC) != -1) {
				cls = cls.substring(cls.lastIndexOf(SRC));

				if (cls.contains("src/main/resources/"))
					cls = cls.split("src.main.resources.")[1].replace("/", "_");
				else if (cls.contains("src/test/resources/"))
					cls = cls.split("src.test.resources.")[1].replace("/", "_");
				else
					cls = cls.split("src.")[1].replace("/", "_");
			} else {
				cls = cls.replace("/", "_");
			}
			bi.setid(prefixForId + cls.replace(".", "_"));
		}
		return bi;
	}

	/**
	 * 
	 * @param pathToCsvDir
	 * @return
	 */
	private static HashMap<String, String> mapNsClass(String pathToCsvDir) {
		HashMap<String, String> nsClassMapForDotNet = null;
		if (new File(pathToCsvDir.split("/IDP_DevopsJSON_Integration/Jenkins_Reports")[0] + NAMESPACECLASSMAP).isFile())
			nsClassMapForDotNet = readCsvFileInWs(
					pathToCsvDir.split("/IDP_DevopsJSON_Integration/Jenkins_Reports")[0] + NAMESPACECLASSMAP);
		else if (new File(
				pathToCsvDir.split("\\\\IDP_DevopsJSON_Integration\\\\Jenkins_Reports")[0] + NAMESPACECLASSMAP)
						.isFile())
			nsClassMapForDotNet = readCsvFileInWs(
					pathToCsvDir.split("\\\\IDP_DevopsJSON_Integration\\\\Jenkins_Reports")[0] + NAMESPACECLASSMAP);
		else
			logger.info("NamespaceClassMap.csv in not required/available !!");
		return nsClassMapForDotNet;
	}

	/**
	 * 
	 * @param filePath
	 * @return
	 */
	private static HashMap<String, String> readCsvFileInWs(String filePath) {
		HashMap<String, String> nsClassMapForDotNet = new HashMap<>();
		BufferedReader br = null;
		String line = "";
		String cvsSplitBy = ",";
		try {
			br = new BufferedReader(new FileReader(filePath));
			while ((line = br.readLine()) != null) {
				String[] kv = line.split(cvsSplitBy);
				nsClassMapForDotNet.put(processKey(kv[0]), kv[1]);
			}

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		} finally {
			if (br != null) {
				try {
					br.close();
				} catch (IOException e) {
					logger.error(e.getMessage(), e);
				}
			}
		}
		return nsClassMapForDotNet;
	}

	private static String processKey(String rawKey) {
		if (rawKey.indexOf('\\') == -1)
			return rawKey;
		return rawKey.substring(1);
	}

	/**
	 * method to parse jobdetails
	 * 
	 * @param inputPath
	 * @param json
	 * @return
	 */
	public static JsonClass convertJobDetail(String inputPath, JsonClass json) {
		BuildDetail buildDetail = null;
		try {
			File file = new File(inputPath);
			JAXBContext jaxbContext = JAXBContext.newInstance(JobDetails.class);
			Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
			JobDetails details = (JobDetails) jaxbUnmarshaller.unmarshal(file);
			buildDetail = new BuildDetail();
			buildDetail.setLastFailedBuildId(details.getLastFailedBuildId());

			buildDetail.setLastBuildId(details.getNumber());
			buildDetail.setLastCompletedBuildId(details.getLastCompletedBuildId());

			buildDetail.setLastSuccessfulBuildId(details.getLastCompletedBuildId());
			buildDetail.setLastUnstableBuildId(details.getLastUnstableBuildId());
			buildDetail.setLastUnsuccessfulBuildId(details.getLastUnsuccessfulBuildId());
			buildDetail.setScore(details.getScore());
			buildDetail.setBuildTime(buildTime);
			buildDetail.setBuiltStatus(buildstatus);
			buildDetail.setTimestamp(timestamp);
			String temp2 = buildDetail.getLastBuildId();
			String temp = details.getUrl().substring(details.getUrl().lastIndexOf("job") + 4,
					details.getUrl().lastIndexOf(temp2) - 1);

			buildDetail.setStageName(temp);
			if (json.getBuildDetails() == null) {
				List<BuildDetail> bd = new ArrayList<>();
				bd.add(buildDetail);
				json.setBuildDetails(bd);
			} else {
				List<BuildDetail> bd = json.getBuildDetails();
				bd.add(buildDetail);
			}
			// logic to have a base URL

			json.setBaseURL(details.getUrl() + "artifact");
			//

			logger.info("Job Details Report Converted Successfully..!!");
		} catch (Exception e) {
			logger.error(CONEVERSIONERROR + inputPath + ERROR + e);
		}
		return json;
	}

	/**
	 * method to convert code coverage
	 * 
	 * @param inputPath
	 * @param json
	 * @return
	 */
	public static JsonClass convertCodeCoverage(String inputPath, JsonClass json) {

		try {
			File file = new File(inputPath);
			JAXBContext jaxbContext = JAXBContext.newInstance(CodeCoverage.class);
			Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
			CodeCoverage details = (CodeCoverage) jaxbUnmarshaller.unmarshal(file);
			Jacoco coverage = new Jacoco();
			coverage.setBranchCoverage(details.getBranchCoverage());
			coverage.setClassCoverage(details.getClassCoverage());
			coverage.setComplexityScore(details.getComplexityScore());
			coverage.setInstructionCoverage(details.getComplexityScore());
			coverage.setLineCoverage(details.getLineCoverage());
			coverage.setMethodCoverage(details.getMethodCoverage());
			codeCoverage.setJacoco(coverage);
			json.setCodecoverage(codeCoverage);
			logger.info("Code Coverage Report Converted Successfully..!!");
		} catch (Exception e) {
			logger.error(CONEVERSIONERROR + inputPath + ERROR + e);
		}
		return json;
	}

	/**
	 * method to convert code checkmarx
	 * 
	 * @param inputPath
	 * @param json
	 * @return
	 */
	public static JsonClass convertCheckmarx(String inputPath, JsonClass json) {
		try {
			File file = new File(inputPath);
			JAXBContext jaxbContext = JAXBContext.newInstance(CxXMLResults.class);
			Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
			CxXMLResults obj = (CxXMLResults) jaxbUnmarshaller.unmarshal(file);
			int high = 0;
			int medium = 0;
			int low = 0;
			for (com.infosys.utilities.checkmarx.CxXMLResults.Query query : obj.getQuery()) {
				for (com.infosys.utilities.checkmarx.CxXMLResults.Query.Result result : query.getResult()) {
					if (result.getSeverity().equalsIgnoreCase("High")) {
						high++;
					} else if (result.getSeverity().equalsIgnoreCase("Medium")) {
						medium++;
					} else if (result.getSeverity().equalsIgnoreCase("Low")) {
						low++;
					}
				}
			}
			Checkmarx checkmarx = new Checkmarx();
			checkmarx.setHigh(high);
			checkmarx.setLow(low);
			checkmarx.setMedium(medium);
			securityTest.setCheckmarx(checkmarx);
			json.setSecurityTest(securityTest);
			logger.info("Checkmarx Report Converted Successfully..!!");
		} catch (Exception e) {
			logger.error(CONEVERSIONERROR + inputPath + ERROR + e);
		}
		return json;
	}

	/**
	 * method to convert acceleratest details
	 * 
	 * @param inputPath
	 * @param json
	 * @return
	 */
	public static JsonClass convertAcceleraTest(String inputPath, JsonClass json) {
		JSONParser parser = new JSONParser();
		int passed = 0;
		int failed = 0;
		int totalnooftests = 0;
		try {
			JSONArray array = (JSONArray) parser.parse(new FileReader(inputPath)); // please
																					// get
																					// the
																					// path
																					// from
																					// the
																					// parameter
																					// XML

			for (Object o : array) {
				JSONObject object = (JSONObject) o;
				JSONArray elements = (JSONArray) object.get("elements");
				for (Object oelements : elements) {
					JSONObject elemobj = (JSONObject) oelements;
					JSONArray steps = (JSONArray) elemobj.get("steps");
					totalnooftests = elements.size();

					boolean flag = false;
					for (int i = 0; i < steps.size(); i++) {
						JSONObject stepsobj = (JSONObject) steps.get(i);
						JSONObject result = (JSONObject) stepsobj.get("result");
						String status = (String) result.get("status");

						if (!status.equalsIgnoreCase("passed")) {
							failed++;
							flag = true;

						}

						else {
							if (i == steps.size() - 1) {
								passed++;
								flag = true;

							}
						}
						if (flag)
							break;
					}
				}

			}
			Accleratest accleratest = new Accleratest();
			accleratest.setTotalTest(totalnooftests);
			accleratest.setPassed(passed);
			accleratest.setFailed(failed);
			Functional ft = json.getFunctionalTest();
			if (ft == null)
				ft = new Functional();
			ft.setAccleratest(accleratest);
			json.setFunctionalTest(ft);
			logger.info("Acceleratest file is converted .");
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}

		return json;
	}

	/**
	 * method to convert itops details
	 * 
	 * @param inputPath
	 * @param json
	 * @return
	 */
	public static JsonClass convertItops(String inputPath, JsonClass json) {
		try {
			File file = new File(inputPath);
			JAXBContext jaxbContext = JAXBContext.newInstance(Itops.class);
			Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
			Itops itops = (Itops) jaxbUnmarshaller.unmarshal(file);
			int totalTest = 0;
			int pass = 0;
			int fail = 0;
			for (Class classes : itops.getSuite().getTest().getClazz()) {
				for (TestMethod testMethod : classes.getTestMethod()) {
					totalTest++;
					if (testMethod.getStatus().equals("PASS"))
						pass++;
					if (testMethod.getStatus().equals("FAIL"))
						fail++;
				}
			}
			com.infosys.json.Itops itopsObj = new com.infosys.json.Itops();
			itopsObj.setTotalTest(totalTest);
			itopsObj.setPass(pass);
			itopsObj.setFail(fail);
			Functional ft = json.getFunctionalTest();
			if (ft == null)
				ft = new Functional();
			ft.setItops(itopsObj);
			json.setFunctionalTest(ft);
			logger.info("iTops Report Converted Successfully..!!");
		} catch (Exception e) {
			logger.error(CONEVERSIONERROR + inputPath + ERROR + e);
		}
		return json;
	}

	/**
	 * method to convert testng details
	 * 
	 * @param inputPath
	 * @param json
	 * @return
	 */
	public static JsonClass converTestng(String inputPath, JsonClass json) {
		try {
			File file = new File(inputPath);
			JAXBContext jaxbContext = JAXBContext.newInstance(Testng.class);
			Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
			Testng testng = (Testng) jaxbUnmarshaller.unmarshal(file);
			int totalTest = 0;
			int pass = 0;
			int fail = 0;
			for (com.infosys.utilities.seleniumtestng.Testng.Suite.Test.Class classes : testng.getSuite().getTest()
					.getClazz()) {
				for (com.infosys.utilities.seleniumtestng.Testng.Suite.Test.Class.TestMethod testMethod : classes
						.getTestMethod()) {
					totalTest++;
					if (testMethod.getStatus().equals("PASS"))
						pass++;
					if (testMethod.getStatus().equals("FAIL"))
						fail++;
				}
			}
			Selenium selenium = new Selenium();
			selenium.setTotalTest(totalTest);
			selenium.setPass(pass);
			selenium.setFail(fail);
			Functional ft = json.getFunctionalTest();
			if (ft == null)
				ft = new Functional();
			ft.setSelenium(selenium);
			json.setFunctionalTest(ft);
			logger.info("TestNg Report Converted Successfully..!!");
		} catch (Exception e) {
			logger.error(CONEVERSIONERROR + inputPath + ERROR + e);
		}
		return json;

	}

	/**
	 * method to convert qualitia details
	 * 
	 * @param inputPath
	 * @param json
	 * @return
	 */
	public static JsonClass converQualitia(String inputPath, JsonClass json) {

		try {
			File file = new File(inputPath);
			JAXBContext jaxbContext = JAXBContext.newInstance(QualitiaTest.class);
			Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
			QualitiaTest qualitiaTest = (QualitiaTest) jaxbUnmarshaller.unmarshal(file);
			int totalTest = 0;
			int pass = 0;
			int fail = 0;
			for (Suite suite : qualitiaTest.getSuite()) {
				for (TCs tcs : suite.gettCs()) {
					for (TC tc : tcs.getTC()) {
						totalTest++;
						if (tc.getStatus() == 1)
							fail++;
						else
							pass++;
					}
				}
			}
			Qualitia qualitia = new Qualitia();
			qualitia.setTotalTest(totalTest);
			qualitia.setPass(pass);
			qualitia.setFail(fail);
			Functional ft = json.getFunctionalTest();
			if (ft == null)
				ft = new Functional();
			ft.setQualitia(qualitia);
			json.setFunctionalTest(ft);
			logger.info("Qualitia Report Converted Successfully..!!");
		} catch (Exception e) {
			logger.error(CONEVERSIONERROR + inputPath + ERROR + e);
		}
		return json;
	}

	/**
	 * 
	 * method to convert robot details
	 * 
	 * @param inputPath
	 * @param json
	 * @return
	 */
	public static JsonClass converRobot(String inputPath, JsonClass json) {
		try {
			File file = new File(inputPath);
			JAXBContext jaxbContext = JAXBContext.newInstance(Robot.class);
			Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();

			Robot robot = (Robot) jaxbUnmarshaller.unmarshal(file);
			Statistics statistics = robot.getStatistics();
			Stats stats = statistics.getTotal();
			RobotJson robotTest = new RobotJson();

			for (Stat statsTemp : stats.getStat()) {
				if (statsTemp.getValue().equals("All Tests")) {
					robotTest.setTotalTest(statsTemp.getFail() + statsTemp.getPass());
					robotTest.setPass(statsTemp.getPass());
					robotTest.setFail(statsTemp.getFail());
					break;
				}
			}
			Functional ft = json.getFunctionalTest();
			if (ft == null)
				ft = new Functional();
			ft.setRobot(robotTest);
			json.setFunctionalTest(ft);
			logger.info("Qualitia Report Converted Successfully..!!");
		} catch (Exception e) {
			logger.error(CONEVERSIONERROR + inputPath + ERROR + e);
		}
		return json;
	}
}