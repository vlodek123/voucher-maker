package cz.tallavla.vouchermaker.daoservice;

import cz.tallavla.vouchermaker.model.repository.CaptureItem;
import cz.tallavla.vouchermaker.model.service.CaptureDTOReturned;
import cz.tallavla.vouchermaker.model.service.CaptureItemDTOReturned;

import java.util.ArrayList;

public interface CaptureItemDAOService {

	public ArrayList<CaptureItemDTOReturned> saveAllCaptureItems(ArrayList<CaptureItemDTOReturned> captureItemListForSave);



}
