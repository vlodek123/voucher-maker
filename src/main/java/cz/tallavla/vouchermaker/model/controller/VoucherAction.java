package cz.tallavla.vouchermaker.model.controller;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.Objects;

@ApiModel(description = "VoucherAction model Information")
public class VoucherAction {

	@ApiModelProperty(value = "VoucherAction action")
	private String action;

	public VoucherAction(String action) {
		this.action = action;
	}

	public VoucherAction() {
	}

	public String getAction() {
		return action;
	}

	public void setAction(String action) {
		this.action = action;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		VoucherAction that = (VoucherAction) o;

		return Objects.equals(action, that.action);
	}

	@Override
	public int hashCode() {
		return action != null ? action.hashCode() : 0;
	}

	@Override
	public String toString() {
		return "VoucherAction{" +
				"action='" + action + '\'' +
				'}';
	}
}
