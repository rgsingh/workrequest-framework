package com.rgsinfotech.model;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "part")
public class Part {

	String name;
	Integer weightInPounds;
	Long serialNumber;
	Integer lengthInCentimeters;
	Integer widthInCentimeters;

	public Part() {

	}

	public Part(String name, Integer weightInPounds, Long serialNumber,
			Integer lengthInCentimeters, Integer widthInCentimeters) {
		super();
		this.name = name;
		this.weightInPounds = weightInPounds;
		this.serialNumber = serialNumber;
		this.lengthInCentimeters = lengthInCentimeters;
		this.widthInCentimeters = widthInCentimeters;
	}

	@XmlElement
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@XmlElement
	public Integer getWeightInPounds() {
		return weightInPounds;
	}

	public void setWeightInPounds(Integer weightInPounds) {
		this.weightInPounds = weightInPounds;
	}

	@XmlElement
	public Long getSerialNumber() {
		return serialNumber;
	}

	public void setSerialNumber(Long serialNumber) {
		this.serialNumber = serialNumber;
	}

	public Integer getLengthInCentimeters() {
		return lengthInCentimeters;
	}

	public void setLengthInCentimeters(Integer lengthInCentimeters) {
		this.lengthInCentimeters = lengthInCentimeters;
	}

	@XmlElement
	public Integer getWidthInCentimeters() {
		return widthInCentimeters;
	}

	public void setWidthInCentimeters(Integer widthInCentimeters) {
		this.widthInCentimeters = widthInCentimeters;
	}

}