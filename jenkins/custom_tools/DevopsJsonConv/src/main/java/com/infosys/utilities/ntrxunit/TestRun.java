//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.8-b130911.1802 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2017.12.20 at 12:10:20 PM IST 
//
package com.infosys.utilities.ntrxunit;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = { "testSettings", "times", "resultSummary", "testDefinitions", "testLists",
		"testEntries", "results" })
@XmlRootElement(name = "TestRun")
public class TestRun {
	@XmlElement(name = "TestSettings", required = true)
	protected TestSettings testSettings;
	@XmlElement(name = "Times", required = true)
	protected TestRun.Times times;
	@XmlElement(name = "ResultSummary", required = true)
	protected TestRun.ResultSummary resultSummary;
	@XmlElement(name = "TestDefinitions", required = true)
	protected TestRun.TestDefinitions testDefinitions;
	@XmlElement(name = "TestLists", required = true)
	protected TestRun.TestLists testLists;
	@XmlElement(name = "TestEntries", required = true)
	protected TestRun.TestEntries testEntries;
	@XmlElement(name = "Results", required = true)
	protected TestRun.Results results;
	@XmlAttribute(name = "id")
	protected String id;
	@XmlAttribute(name = "name")
	protected String name;
	@XmlAttribute(name = "runUser")
	protected String runUser;

	public TestSettings getTestSettings() {
		return testSettings;
	}

	public void setTestSettings(TestSettings value) {
		this.testSettings = value;
	}

	public TestRun.Times getTimes() {
		return times;
	}

	public void setTimes(TestRun.Times value) {
		this.times = value;
	}

	public TestRun.ResultSummary getResultSummary() {
		return resultSummary;
	}

	public void setResultSummary(TestRun.ResultSummary value) {
		this.resultSummary = value;
	}

	public TestRun.TestDefinitions getTestDefinitions() {
		return testDefinitions;
	}

	public void setTestDefinitions(TestRun.TestDefinitions value) {
		this.testDefinitions = value;
	}

	public TestRun.TestLists getTestLists() {
		return testLists;
	}

	public void setTestLists(TestRun.TestLists value) {
		this.testLists = value;
	}

	public TestRun.TestEntries getTestEntries() {
		return testEntries;
	}

	public void setTestEntries(TestRun.TestEntries value) {
		this.testEntries = value;
	}

	public TestRun.Results getResults() {
		return results;
	}

	public void setResults(TestRun.Results value) {
		this.results = value;
	}

	public String getId() {
		return id;
	}

	public void setId(String value) {
		this.id = value;
	}

	public String getName() {
		return name;
	}

	public void setName(String value) {
		this.name = value;
	}

	public String getRunUser() {
		return runUser;
	}

	public void setRunUser(String value) {
		this.runUser = value;
	}

	@XmlAccessorType(XmlAccessType.FIELD)
	@XmlType(name = "", propOrder = { "counters", "runInfos", "resultFiles" })
	public static class ResultSummary {
		@XmlElement(name = "Counters", required = true)
		protected TestRun.ResultSummary.Counters counters;
		@XmlElement(name = "RunInfos", required = true)
		protected TestRun.ResultSummary.RunInfos runInfos;
		@XmlElement(name = "ResultFiles", required = true)
		protected TestRun.ResultSummary.ResultFiles resultFiles;
		@XmlAttribute(name = "outcome")
		protected String outcome;

		public TestRun.ResultSummary.Counters getCounters() {
			return counters;
		}

		public void setCounters(TestRun.ResultSummary.Counters value) {
			this.counters = value;
		}

		public TestRun.ResultSummary.RunInfos getRunInfos() {
			return runInfos;
		}

		public void setRunInfos(TestRun.ResultSummary.RunInfos value) {
			this.runInfos = value;
		}

		public TestRun.ResultSummary.ResultFiles getResultFiles() {
			return resultFiles;
		}

		public void setResultFiles(TestRun.ResultSummary.ResultFiles value) {
			this.resultFiles = value;
		}

		public String getOutcome() {
			return outcome;
		}

		public void setOutcome(String value) {
			this.outcome = value;
		}

		@XmlAccessorType(XmlAccessType.FIELD)
		@XmlType(name = "")
		public static class Counters {
			@XmlAttribute(name = "aborted")
			protected Integer aborted;
			@XmlAttribute(name = "completed")
			protected Integer completed;
			@XmlAttribute(name = "disconnected")
			protected Integer disconnected;
			@XmlAttribute(name = "error")
			protected Integer error;
			@XmlAttribute(name = "executed")
			protected Integer executed;
			@XmlAttribute(name = "failed")
			protected Integer failed;
			@XmlAttribute(name = "inProgress")
			protected Integer inProgress;
			@XmlAttribute(name = "inconclusive")
			protected Integer inconclusive;
			@XmlAttribute(name = "notExecuted")
			protected Integer notExecuted;
			@XmlAttribute(name = "notRunnable")
			protected Integer notRunnable;
			@XmlAttribute(name = "passed")
			protected Integer passed;
			@XmlAttribute(name = "passedButRunAborted")
			protected Integer passedButRunAborted;
			@XmlAttribute(name = "pending")
			protected Integer pending;
			@XmlAttribute(name = "timeout")
			protected Integer timeout;
			@XmlAttribute(name = "total")
			protected Integer total;
			@XmlAttribute(name = "warning")
			protected Integer warning;

			public Integer getAborted() {
				return aborted;
			}

			public void setAborted(Integer value) {
				this.aborted = value;
			}

			public Integer getCompleted() {
				return completed;
			}

			public void setCompleted(Integer value) {
				this.completed = value;
			}

			public Integer getDisconnected() {
				return disconnected;
			}

			public void setDisconnected(Integer value) {
				this.disconnected = value;
			}

			public Integer getError() {
				return error;
			}

			public void setError(Integer value) {
				this.error = value;
			}

			public Integer getExecuted() {
				return executed;
			}

			public void setExecuted(Integer value) {
				this.executed = value;
			}

			public Integer getFailed() {
				return failed;
			}

			public void setFailed(Integer value) {
				this.failed = value;
			}

			public Integer getInProgress() {
				return inProgress;
			}

			public void setInProgress(Integer value) {
				this.inProgress = value;
			}

			public Integer getInconclusive() {
				return inconclusive;
			}

			public void setInconclusive(Integer value) {
				this.inconclusive = value;
			}

			public Integer getNotExecuted() {
				return notExecuted;
			}

			public void setNotExecuted(Integer value) {
				this.notExecuted = value;
			}

			public Integer getNotRunnable() {
				return notRunnable;
			}

			public void setNotRunnable(Integer value) {
				this.notRunnable = value;
			}

			public Integer getPassed() {
				return passed;
			}

			public void setPassed(Integer value) {
				this.passed = value;
			}

			public Integer getPassedButRunAborted() {
				return passedButRunAborted;
			}

			public void setPassedButRunAborted(Integer value) {
				this.passedButRunAborted = value;
			}

			public Integer getPending() {
				return pending;
			}

			public void setPending(Integer value) {
				this.pending = value;
			}

			public Integer getTimeout() {
				return timeout;
			}

			public void setTimeout(Integer value) {
				this.timeout = value;
			}

			public Integer getTotal() {
				return total;
			}

			public void setTotal(Integer value) {
				this.total = value;
			}

			public Integer getWarning() {
				return warning;
			}

			public void setWarning(Integer value) {
				this.warning = value;
			}
		}

		@XmlAccessorType(XmlAccessType.FIELD)
		@XmlType(name = "", propOrder = { "resultFile" })
		public static class ResultFiles {
			@XmlElement(name = "ResultFile", required = true)
			protected TestRun.ResultSummary.ResultFiles.ResultFile resultFile;

			public TestRun.ResultSummary.ResultFiles.ResultFile getResultFile() {
				return resultFile;
			}

			public void setResultFile(TestRun.ResultSummary.ResultFiles.ResultFile value) {
				this.resultFile = value;
			}

			@XmlAccessorType(XmlAccessType.FIELD)
			@XmlType(name = "")
			public static class ResultFile {
				@XmlAttribute(name = "path")
				protected String path;

				public String getPath() {
					return path;
				}

				public void setPath(String value) {
					this.path = value;
				}
			}
		}

		@XmlAccessorType(XmlAccessType.FIELD)
		@XmlType(name = "", propOrder = { "runInfo" })
		public static class RunInfos {
			@XmlElement(name = "RunInfo", required = true)
			protected TestRun.ResultSummary.RunInfos.RunInfo runInfo;

			public TestRun.ResultSummary.RunInfos.RunInfo getRunInfo() {
				return runInfo;
			}

			public void setRunInfo(TestRun.ResultSummary.RunInfos.RunInfo value) {
				this.runInfo = value;
			}

			@XmlAccessorType(XmlAccessType.FIELD)
			@XmlType(name = "", propOrder = { "text" })
			public static class RunInfo {
				@XmlElement(name = "Text", required = true)
				protected String text;
				@XmlAttribute(name = "computerName")
				protected String computerName;
				@XmlAttribute(name = "outcome")
				protected String outcome;
				@XmlAttribute(name = "timestamp")
				protected String timestamp;

				public String getText() {
					return text;
				}

				public void setText(String value) {
					this.text = value;
				}

				public String getComputerName() {
					return computerName;
				}

				public void setComputerName(String value) {
					this.computerName = value;
				}

				public String getOutcome() {
					return outcome;
				}

				public void setOutcome(String value) {
					this.outcome = value;
				}

				public String getTimestamp() {
					return timestamp;
				}

				public void setTimestamp(String value) {
					this.timestamp = value;
				}
			}
		}
	}

	@XmlAccessorType(XmlAccessType.FIELD)
	@XmlType(name = "", propOrder = { "unitTestResult" })
	public static class Results {
		@XmlElement(name = "UnitTestResult", required = true)
		protected List<TestRun.Results.UnitTestResult> unitTestResult;

		public List<TestRun.Results.UnitTestResult> getUnitTestResult() {
			if (unitTestResult == null) {
				unitTestResult = new ArrayList<TestRun.Results.UnitTestResult>();
			}
			return this.unitTestResult;
		}

		@XmlAccessorType(XmlAccessType.FIELD)
		@XmlType(name = "")
		public static class UnitTestResult {
			@XmlAttribute(name = "computerName")
			protected String computerName;
			@XmlAttribute(name = "duration")
			protected String duration;
			@XmlAttribute(name = "endTime")
			protected String endTime;
			@XmlAttribute(name = "executionId")
			protected String executionId;
			@XmlAttribute(name = "outcome")
			protected String outcome;
			@XmlAttribute(name = "relativeResultsDirectory")
			protected String relativeResultsDirectory;
			@XmlAttribute(name = "startTime")
			protected String startTime;
			@XmlAttribute(name = "testId")
			protected String testId;
			@XmlAttribute(name = "testListId")
			protected String testListId;
			@XmlAttribute(name = "testName")
			protected String testName;
			@XmlAttribute(name = "testType")
			protected String testType;

			public String getComputerName() {
				return computerName;
			}

			public void setComputerName(String value) {
				this.computerName = value;
			}

			public String getDuration() {
				return duration;
			}

			public void setDuration(String value) {
				this.duration = value;
			}

			public String getEndTime() {
				return endTime;
			}

			public void setEndTime(String value) {
				this.endTime = value;
			}

			public String getExecutionId() {
				return executionId;
			}

			public void setExecutionId(String value) {
				this.executionId = value;
			}

			public String getOutcome() {
				return outcome;
			}

			public void setOutcome(String value) {
				this.outcome = value;
			}

			public String getRelativeResultsDirectory() {
				return relativeResultsDirectory;
			}

			public void setRelativeResultsDirectory(String value) {
				this.relativeResultsDirectory = value;
			}

			public String getStartTime() {
				return startTime;
			}

			public void setStartTime(String value) {
				this.startTime = value;
			}

			public String getTestId() {
				return testId;
			}

			public void setTestId(String value) {
				this.testId = value;
			}

			public String getTestListId() {
				return testListId;
			}

			public void setTestListId(String value) {
				this.testListId = value;
			}

			public String getTestName() {
				return testName;
			}

			public void setTestName(String value) {
				this.testName = value;
			}

			public String getTestType() {
				return testType;
			}

			public void setTestType(String value) {
				this.testType = value;
			}
		}
	}

	@XmlAccessorType(XmlAccessType.FIELD)
	@XmlType(name = "", propOrder = { "unitTest" })
	public static class TestDefinitions {
		@XmlElement(name = "UnitTest", required = true)
		protected List<TestRun.TestDefinitions.UnitTest> unitTest;

		public List<TestRun.TestDefinitions.UnitTest> getUnitTest() {
			if (unitTest == null) {
				unitTest = new ArrayList<TestRun.TestDefinitions.UnitTest>();
			}
			return this.unitTest;
		}

		@XmlAccessorType(XmlAccessType.FIELD)
		@XmlType(name = "", propOrder = { "execution", "testMethod" })
		public static class UnitTest {
			@XmlElement(name = "Execution", required = true)
			protected TestRun.TestDefinitions.UnitTest.Execution execution;
			@XmlElement(name = "TestMethod", required = true)
			protected TestRun.TestDefinitions.UnitTest.TestMethod testMethod;
			@XmlAttribute(name = "id")
			protected String id;
			@XmlAttribute(name = "name")
			protected String name;
			@XmlAttribute(name = "storage")
			protected String storage;

			public TestRun.TestDefinitions.UnitTest.Execution getExecution() {
				return execution;
			}

			public void setExecution(TestRun.TestDefinitions.UnitTest.Execution value) {
				this.execution = value;
			}

			public TestRun.TestDefinitions.UnitTest.TestMethod getTestMethod() {
				return testMethod;
			}

			public void setTestMethod(TestRun.TestDefinitions.UnitTest.TestMethod value) {
				this.testMethod = value;
			}

			public String getId() {
				return id;
			}

			public void setId(String value) {
				this.id = value;
			}

			public String getName() {
				return name;
			}

			public void setName(String value) {
				this.name = value;
			}

			public String getStorage() {
				return storage;
			}

			public void setStorage(String value) {
				this.storage = value;
			}

			@XmlAccessorType(XmlAccessType.FIELD)
			@XmlType(name = "")
			public static class Execution {
				@XmlAttribute(name = "id")
				protected String id;

				public String getId() {
					return id;
				}

				public void setId(String value) {
					this.id = value;
				}
			}

			@XmlAccessorType(XmlAccessType.FIELD)
			@XmlType(name = "")
			public static class TestMethod {
				@XmlAttribute(name = "adapterTypeName")
				protected String adapterTypeName;
				@XmlAttribute(name = "className")
				protected String className;
				@XmlAttribute(name = "codeBase")
				protected String codeBase;
				@XmlAttribute(name = "name")
				protected String name;

				public String getAdapterTypeName() {
					return adapterTypeName;
				}

				public void setAdapterTypeName(String value) {
					this.adapterTypeName = value;
				}

				public String getClassName() {
					return className;
				}

				public void setClassName(String value) {
					this.className = value;
				}

				public String getCodeBase() {
					return codeBase;
				}

				public void setCodeBase(String value) {
					this.codeBase = value;
				}

				public String getName() {
					return name;
				}

				public void setName(String value) {
					this.name = value;
				}
			}
		}
	}

	@XmlAccessorType(XmlAccessType.FIELD)
	@XmlType(name = "", propOrder = { "testEntry" })
	public static class TestEntries {
		@XmlElement(name = "TestEntry", required = true)
		protected List<TestRun.TestEntries.TestEntry> testEntry;

		public List<TestRun.TestEntries.TestEntry> getTestEntry() {
			if (testEntry == null) {
				testEntry = new ArrayList<TestRun.TestEntries.TestEntry>();
			}
			return this.testEntry;
		}

		@XmlAccessorType(XmlAccessType.FIELD)
		@XmlType(name = "")
		public static class TestEntry {
			@XmlAttribute(name = "executionId")
			protected String executionId;
			@XmlAttribute(name = "testId")
			protected String testId;
			@XmlAttribute(name = "testListId")
			protected String testListId;

			public String getExecutionId() {
				return executionId;
			}

			public void setExecutionId(String value) {
				this.executionId = value;
			}

			public String getTestId() {
				return testId;
			}

			public void setTestId(String value) {
				this.testId = value;
			}

			public String getTestListId() {
				return testListId;
			}

			public void setTestListId(String value) {
				this.testListId = value;
			}
		}
	}

	@XmlAccessorType(XmlAccessType.FIELD)
	@XmlType(name = "", propOrder = { "testList" })
	public static class TestLists {
		@XmlElement(name = "TestList", required = true)
		protected List<TestRun.TestLists.TestList> testList;

		public List<TestRun.TestLists.TestList> getTestList() {
			if (testList == null) {
				testList = new ArrayList<TestRun.TestLists.TestList>();
			}
			return this.testList;
		}

		@XmlAccessorType(XmlAccessType.FIELD)
		@XmlType(name = "")
		public static class TestList {
			@XmlAttribute(name = "id")
			protected String id;
			@XmlAttribute(name = "name")
			protected String name;

			public String getId() {
				return id;
			}

			public void setId(String value) {
				this.id = value;
			}

			public String getName() {
				return name;
			}

			public void setName(String value) {
				this.name = value;
			}
		}
	}

	
	@XmlAccessorType(XmlAccessType.FIELD)
	@XmlType(name = "")
	public static class Times {
		@XmlAttribute(name = "creation")
		protected String creation;
		@XmlAttribute(name = "finish")
		protected String finish;
		@XmlAttribute(name = "queuing")
		protected String queuing;
		@XmlAttribute(name = "start")
		protected String start;

		public String getCreation() {
			return creation;
		}

		public void setCreation(String value) {
			this.creation = value;
		}

		public String getFinish() {
			return finish;
		}

		public void setFinish(String value) {
			this.finish = value;
		}

		public String getQueuing() {
			return queuing;
		}

		public void setQueuing(String value) {
			this.queuing = value;
		}

		public String getStart() {
			return start;
		}

		public void setStart(String value) {
			this.start = value;
		}
	}
}
