package be.vdab.personeel.web;

import java.math.BigDecimal;

import javax.validation.constraints.Digits;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.NumberFormat;
import org.springframework.format.annotation.NumberFormat.Style;

public class OpslagForm {

	@NotNull
	@Min(1)
	@NumberFormat(style = Style.NUMBER)
	@Digits(integer = 10, fraction = 2)
	private BigDecimal opslag;

	public BigDecimal getOpslag() {
		return opslag;
	}

	public void setOpslag(BigDecimal opslag) {
		this.opslag = opslag;
	}

}
