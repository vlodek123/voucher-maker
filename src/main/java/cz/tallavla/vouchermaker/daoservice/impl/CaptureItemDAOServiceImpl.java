package cz.tallavla.vouchermaker.daoservice.impl;

import cz.tallavla.vouchermaker.daoservice.CaptureItemDAOService;
import cz.tallavla.vouchermaker.model.repository.CaptureItem;
import cz.tallavla.vouchermaker.model.service.CaptureDTOReturned;
import cz.tallavla.vouchermaker.model.service.CaptureItemDTOReturned;
import cz.tallavla.vouchermaker.repository.CaptureItemRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
@RequiredArgsConstructor
@Slf4j
public class CaptureItemDAOServiceImpl implements CaptureItemDAOService {

	ModelMapper modelMapper = new ModelMapper();
	@Autowired
	private CaptureItemRepository captureItemRepository;


	@Override
	public ArrayList<CaptureItemDTOReturned> saveAllCaptureItems(ArrayList<CaptureItemDTOReturned> captureItemListForSave) {
		ArrayList<CaptureItem> captureItems = new ArrayList<>();

		for (CaptureItemDTOReturned item: captureItemListForSave
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
