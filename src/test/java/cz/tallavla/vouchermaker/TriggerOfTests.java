package cz.tallavla.vouchermaker;

import cz.tallavla.vouchermaker.unit.controller.ControllerUnitTest;
import cz.tallavla.vouchermaker.unit.daoservice.VoucherDOAServiceUnitTest;
import cz.tallavla.vouchermaker.unit.mappers.MappersUnitTest;
import cz.tallavla.vouchermaker.unit.service.VoucherServiceUnitTest;
import org.junit.platform.suite.api.SelectClasses;
import org.junit.platform.suite.api.SuiteDisplayName;
import org.junit.runner.RunWith;

@RunWith(org.junit.platform.runner.JUnitPlatform.class)
@SuiteDisplayName("Voucher Maker Tests")
@SelectClasses({
		MappersUnitTest.class,
		VoucherServiceUnitTest.class,
		VoucherDOAServiceUnitTest.class,
		ControllerUnitTest.class
})
public class TriggerOfTests {
}
