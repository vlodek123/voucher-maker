package cz.tallavla.vouchermaker.daoservice.impl;

import cz.tallavla.vouchermaker.controller.VoucherController;
import cz.tallavla.vouchermaker.daoservice.CaptureItemDAOService;
import cz.tallavla.vouchermaker.model.repository.CaptureItem;
import cz.tallavla.vouchermaker.model.service.CaptureItemDTOReturned;
import cz.tallavla.vouchermaker.repository.CaptureItemRepository;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class CaptureItemDAOServiceImpl implements CaptureItemDAOService {

	private final static Logger log = LoggerFactory.getLogger(VoucherController.class);
	ModelMapper modelMapper = new ModelMapper();
	@Autowired
	private CaptureItemRepository captureItemRepository;


	@Override
	public ArrayList<CaptureItemDTOReturned> saveAllCaptureItems(ArrayList<CaptureItemDTOReturned> captureItemsForSave) {
		ArrayList<CaptureItem> captureItems = new ArrayList<>();

		for (CaptureItemDTOReturned item: captureItemsForSave
			 ) {
			captureItems.add(modelMapper.map(item, CaptureItem.class));
		}
		ArrayList<CaptureItemDTOReturned> captureItemDTOReturnedArrayList = new ArrayList<>();
		for (CaptureItem item: captureItemRepository.saveAll(captureItems)
			 ) {
			captureItemDTOReturnedArrayList.add(modelMapper.map(item, CaptureItemDTOReturned.class));
		}

		return captureItemDTOReturnedArrayList;
	}


}
