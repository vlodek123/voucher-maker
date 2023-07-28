package cz.tallavla.vouchermaker.model.controller;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.Objects;

@ApiModel(description = "InformationResponse model Information")
public class InformationResponse {
	@ApiModelProperty(value = "InformationResponse info")
	private String info;

	@ApiModelProperty(value = "InformationResponse id")
	private String id;

	public static InformationResponseBuilder builder() {
		return new InformationResponseBuilder();
	}

	public String getInfo() {
		return this.info;
	}

	public String getId() {
		return this.id;
	}

	public void setInfo(final String info) {
		this.info = info;
	}

	public void setId(final String id) {
		this.id = id;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		InformationResponse that = (InformationResponse) o;

		if (!Objects.equals(info, that.info)) return false;
		return Objects.equals(id, that.id);
	}

	protected boolean canEqual(final Object other) {
		return other instanceof InformationResponse;
	}

	@Override
	public int hashCode() {
		int result = info != null ? info.hashCode() : 0;
		result = 31 * result + (id != null ? id.hashCode() : 0);
		return result;
	}

	@Override
	public String toString() {
		return "InformationResponse{" +
				"info='" + info + '\'' +
				", id='" + id + '\'' +
				'}';
	}

	public InformationResponse() {
	}

	public InformationResponse(final String info, final String id) {
		this.info = info;
		this.id = id;
	}

	public static class InformationResponseBuilder {
		private String info;
		private String id;

		InformationResponseBuilder() {
		}

		public InformationResponseBuilder info(final String info) {
			this.info = info;
			return this;
		}

		public InformationResponseBuilder id(final String id) {
			this.id = id;
			return this;
		}

		public InformationResponse build() {
			return new InformationResponse(this.info, this.id);
		}

		public String toString() {
			return "InformationResponse.InformationResponseBuilder(info=" + this.info + ", id=" + this.id + ")";
		}
	}

}
