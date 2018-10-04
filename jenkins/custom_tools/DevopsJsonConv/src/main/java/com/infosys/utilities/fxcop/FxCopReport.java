//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, vhudson-jaxb-ri-2.2-146 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2016.08.18 at 05:53:53 PM IST 
//
package com.infosys.utilities.fxcop;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.XmlValue;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = { "targets", "rules", "localized", "exceptions" })
@XmlRootElement(name = "FxCopReport")
public class FxCopReport {
	@XmlElement(name = "Targets", required = true)
	protected Targets targets;
	@XmlElement(name = "Rules", required = true)
	protected FxCopReport.Rules rules;
	@XmlElement(name = "Localized", required = true)
	protected FxCopReport.Localized localized;
	@XmlElement(name = "Exceptions", required = true)
	protected FxCopReport.Exceptions exceptions;
	@XmlAttribute(name = "Version")
	protected Float version;

	public Targets getTargets() {
		return targets;
	}

	public void setTargets(Targets value) {
		this.targets = value;
	}

	public FxCopReport.Rules getRules() {
		return rules;
	}

	public void setRules(FxCopReport.Rules value) {
		this.rules = value;
	}

	public FxCopReport.Localized getLocalized() {
		return localized;
	}

	public void setLocalized(FxCopReport.Localized value) {
		this.localized = value;
	}

	public FxCopReport.Exceptions getExceptions() {
		return exceptions;
	}

	public void setExceptions(FxCopReport.Exceptions value) {
		this.exceptions = value;
	}

	public Float getVersion() {
		return version;
	}

	public void setVersion(Float value) {
		this.version = value;
	}

	@XmlAccessorType(XmlAccessType.FIELD)
	@XmlType(name = "", propOrder = { "exception" })
	public static class Exceptions {
		@XmlElement(name = "Exception", required = true)
		protected FxCopReport.Exceptions.Exception exception;

		public FxCopReport.Exceptions.Exception getException() {
			return exception;
		}

		public void setException(FxCopReport.Exceptions.Exception value) {
			this.exception = value;
		}

		@XmlAccessorType(XmlAccessType.FIELD)
		@XmlType(name = "", propOrder = { "type", "exceptionMessage", "stackTrace" })
		public static class Exception {
			@XmlElement(name = "Type", required = true)
			protected java.lang.String type;
			@XmlElement(name = "ExceptionMessage", required = true)
			protected java.lang.String exceptionMessage;
			@XmlElement(name = "StackTrace", required = true)
			protected java.lang.String stackTrace;
			@XmlAttribute(name = "Keyword")
			protected java.lang.String keyword;
			@XmlAttribute(name = "Kind")
			protected java.lang.String kind;
			@XmlAttribute(name = "TypeName")
			protected java.lang.String typeName;
			@XmlAttribute(name = "Category")
			protected java.lang.String category;
			@XmlAttribute(name = "CheckId")
			protected java.lang.String checkId;
			@XmlAttribute(name = "Target")
			protected java.lang.String target;

			public java.lang.String getType() {
				return type;
			}

			public void setType(java.lang.String value) {
				this.type = value;
			}

			public java.lang.String getExceptionMessage() {
				return exceptionMessage;
			}

			public void setExceptionMessage(java.lang.String value) {
				this.exceptionMessage = value;
			}

			public java.lang.String getStackTrace() {
				return stackTrace;
			}

			public void setStackTrace(java.lang.String value) {
				this.stackTrace = value;
			}

			public java.lang.String getKeyword() {
				return keyword;
			}

			public void setKeyword(java.lang.String value) {
				this.keyword = value;
			}

			public java.lang.String getKind() {
				return kind;
			}

			public void setKind(java.lang.String value) {
				this.kind = value;
			}

			public java.lang.String getTypeName() {
				return typeName;
			}

			public void setTypeName(java.lang.String value) {
				this.typeName = value;
			}

			public java.lang.String getCategory() {
				return category;
			}

			public void setCategory(java.lang.String value) {
				this.category = value;
			}

			public java.lang.String getCheckId() {
				return checkId;
			}

			public void setCheckId(java.lang.String value) {
				this.checkId = value;
			}

			public java.lang.String getTarget() {
				return target;
			}

			public void setTarget(java.lang.String value) {
				this.target = value;
			}
		}
	}

	@XmlAccessorType(XmlAccessType.FIELD)
	@XmlType(name = "", propOrder = { "string" })
	public static class Localized {
		@XmlElement(name = "String")
		protected List<FxCopReport.Localized.String> string;

		public List<FxCopReport.Localized.String> getString() {
			if (string == null) {
				string = new ArrayList<FxCopReport.Localized.String>();
			}
			return this.string;
		}

		@XmlAccessorType(XmlAccessType.FIELD)
		@XmlType(name = "", propOrder = { "value" })
		public static class String {
			@XmlValue
			protected java.lang.String value;
			@XmlAttribute(name = "Key")
			protected java.lang.String key;

			public java.lang.String getValue() {
				return value;
			}

			public void setValue(java.lang.String value) {
				this.value = value;
			}

			public java.lang.String getKey() {
				return key;
			}

			public void setKey(java.lang.String value) {
				this.key = value;
			}
		}
	}

	@XmlAccessorType(XmlAccessType.FIELD)
	@XmlType(name = "", propOrder = { "rule" })
	public static class Rules {
		@XmlElement(name = "Rule")
		protected List<FxCopReport.Rules.Rule> rule;

		public List<FxCopReport.Rules.Rule> getRule() {
			if (rule == null) {
				rule = new ArrayList<FxCopReport.Rules.Rule>();
			}
			return this.rule;
		}

		@XmlAccessorType(XmlAccessType.FIELD)
		@XmlType(name = "", propOrder = { "name", "description", "resolution", "owner", "url", "email", "messageLevel",
				"file" })
		public static class Rule {
			@XmlElement(name = "Name", required = true)
			protected java.lang.String name;
			@XmlElement(name = "Description", required = true)
			protected java.lang.String description;
			@XmlElement(name = "Resolution")
			protected List<FxCopReport.Rules.Rule.Resolution> resolution;
			@XmlElement(name = "Owner", required = true)
			protected java.lang.String owner;
			@XmlElement(name = "Url", required = true)
			@XmlSchemaType(name = "anyURI")
			protected java.lang.String url;
			@XmlElement(name = "Email", required = true)
			protected java.lang.String email;
			@XmlElement(name = "MessageLevel", required = true)
			protected FxCopReport.Rules.Rule.MessageLevel messageLevel;
			@XmlElement(name = "File", required = true)
			protected FxCopReport.Rules.Rule.File file;
			@XmlAttribute(name = "TypeName")
			protected java.lang.String typeName;
			@XmlAttribute(name = "Category")
			protected java.lang.String category;
			@XmlAttribute(name = "CheckId")
			protected java.lang.String checkId;

			public java.lang.String getName() {
				return name;
			}

			public void setName(java.lang.String value) {
				this.name = value;
			}

			public java.lang.String getDescription() {
				return description;
			}

			public void setDescription(java.lang.String value) {
				this.description = value;
			}

			public List<FxCopReport.Rules.Rule.Resolution> getResolution() {
				if (resolution == null) {
					resolution = new ArrayList<FxCopReport.Rules.Rule.Resolution>();
				}
				return this.resolution;
			}

			public java.lang.String getOwner() {
				return owner;
			}

			public void setOwner(java.lang.String value) {
				this.owner = value;
			}

			public java.lang.String getUrl() {
				return url;
			}

			public void setUrl(java.lang.String value) {
				this.url = value;
			}

			public java.lang.String getEmail() {
				return email;
			}

			public void setEmail(java.lang.String value) {
				this.email = value;
			}

			public FxCopReport.Rules.Rule.MessageLevel getMessageLevel() {
				return messageLevel;
			}

			public void setMessageLevel(FxCopReport.Rules.Rule.MessageLevel value) {
				this.messageLevel = value;
			}

			public FxCopReport.Rules.Rule.File getFile() {
				return file;
			}

			public void setFile(FxCopReport.Rules.Rule.File value) {
				this.file = value;
			}

			public java.lang.String getTypeName() {
				return typeName;
			}

			public void setTypeName(java.lang.String value) {
				this.typeName = value;
			}

			public java.lang.String getCategory() {
				return category;
			}

			public void setCategory(java.lang.String value) {
				this.category = value;
			}

			public java.lang.String getCheckId() {
				return checkId;
			}

			public void setCheckId(java.lang.String value) {
				this.checkId = value;
			}

			@XmlAccessorType(XmlAccessType.FIELD)
			@XmlType(name = "", propOrder = { "value" })
			public static class File {
				@XmlValue
				protected java.lang.String value;
				@XmlAttribute(name = "Name")
				protected java.lang.String name;
				@XmlAttribute(name = "Version")
				protected java.lang.String version;

				public java.lang.String getValue() {
					return value;
				}

				public void setValue(java.lang.String value) {
					this.value = value;
				}

				public java.lang.String getName() {
					return name;
				}

				public void setName(java.lang.String value) {
					this.name = value;
				}

				public java.lang.String getVersion() {
					return version;
				}

				public void setVersion(java.lang.String value) {
					this.version = value;
				}
			}

			@XmlAccessorType(XmlAccessType.FIELD)
			@XmlType(name = "", propOrder = { "value" })
			public static class MessageLevel {
				@XmlValue
				protected java.lang.String value;
				@XmlAttribute(name = "Certainty")
				protected Byte certainty;

				public java.lang.String getValue() {
					return value;
				}

				public void setValue(java.lang.String value) {
					this.value = value;
				}

				public Byte getCertainty() {
					return certainty;
				}

				public void setCertainty(Byte value) {
					this.certainty = value;
				}
			}

			@XmlAccessorType(XmlAccessType.FIELD)
			@XmlType(name = "", propOrder = { "value" })
			public static class Resolution {
				@XmlValue
				protected java.lang.String value;
				@XmlAttribute(name = "Name")
				protected java.lang.String name;

				public java.lang.String getValue() {
					return value;
				}

				public void setValue(java.lang.String value) {
					this.value = value;
				}

				public java.lang.String getName() {
					return name;
				}

				public void setName(java.lang.String value) {
					this.name = value;
				}
			}
		}
	}

	}
