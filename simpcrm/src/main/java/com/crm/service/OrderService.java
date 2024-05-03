package com.crm.service;

import com.crm.client.FileStorageClient;
import com.crm.dto.FileStorage;
import com.crm.dto.ITServiceDTO;
import com.crm.dto.OrderDTO;
import com.crm.dto.ProgramDTO;
import com.crm.entity.ITService;
import com.crm.entity.Order;
import com.crm.entity.Program;
import com.crm.reposotiry.*;
import com.crm.uploadClass.UploadClass;
import jakarta.persistence.EntityNotFoundException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderService {

    public final OrderRepository orderRepository;

    public final ITServiceRepository itServicesRepository;

    public final ProgramRepository programRepository;

    public final StatusRepository statusRepository;

    public final UserRepository userRepository;

    public final ITServiceService itServiceService;

    public final ProgramService programService;

    public final FileStorageClient fileStorageClient;

    public final FileStorageService fileStorageService;

    public List<OrderDTO> findAllOrders() {
        List<Order> orders = orderRepository.findAll();
        ArrayList<OrderDTO> orderDTOList = new ArrayList<>();
        for (Order order : orders) {
            orderDTOList.add(makeOrderDTOFromOrder(order));
        }
        return orderDTOList;
    }

    public OrderDTO saveOrder(OrderDTO orderDTO) throws IOException {
        Order savedOrder = orderRepository.save(makeOrderFromOrderDTO(orderDTO, new Order()));
        return makeOrderDTOFromOrder(savedOrder);
    }

    public void deleteOrder(Long id) {
        orderRepository.deleteById(id);
    }

    public OrderDTO editOrder(OrderDTO orderDTO) throws IOException {
        Order order = orderRepository.findById(orderDTO.getId()).orElseThrow(EntityNotFoundException::new);
        orderRepository.save(makeOrderFromOrderDTO(orderDTO, order));
        return orderDTO;
    }

    private OrderDTO makeOrderDTOFromOrder(Order savedOrder) {
        OrderDTO responseOrderDto = new OrderDTO();

        responseOrderDto.setId(savedOrder.getId());
        responseOrderDto.setOrderName(savedOrder.getOrderName());
        responseOrderDto.setPriority(savedOrder.getPriority());
        responseOrderDto.setStatus(savedOrder.getStatus());
        responseOrderDto.setDescription(savedOrder.getDescription());
        responseOrderDto.setComments(savedOrder.getComments());
        responseOrderDto.setClient(savedOrder.getClient());
        responseOrderDto.setUsers(savedOrder.getUser());
        responseOrderDto.setItServices(getITServicesDTOList(savedOrder.getItServices()));
        responseOrderDto.setPrograms(getProgramDTOList(savedOrder.getPrograms()));
        responseOrderDto.setFileStorages(fileStorageClient.getFileByOrderId(savedOrder.getId()));

        return responseOrderDto;
    }

    private Order makeOrderFromOrderDTO(OrderDTO orderDTO, Order order) throws IOException {
        order.setId(orderDTO.getId());
        order.setOrderName(orderDTO.getOrderName());
        order.setDescription(orderDTO.getDescription());
        order.setPriority(orderDTO.getPriority());
        order.setComments(orderDTO.getComments());
        order.setClient(orderDTO.getClient());
        order.setStatus(orderDTO.getStatus());
        if(orderDTO.getUsers()!=null){
            order.setUser(orderDTO.getUsers());
        }
        order.setItServices(getITServicesList(orderDTO.getItServices()));
        order.setPrograms(getProgramList(orderDTO.getPrograms()));
        if(orderDTO.getFileStorages()!=null) {
            List<UploadClass> uploadClasses = makeAnUploadClassList(fileStorageClient.getFileByOrderId(orderDTO.getId()));
            for (UploadClass uploadClass : uploadClasses) {
                fileStorageClient.uploadFile(uploadClass);
            }
        }
        return order;
    }

    private ArrayList<ITServiceDTO> getITServicesDTOList(List<ITService> itServicesList){
        ArrayList<ITServiceDTO> itServicesDTOList = new ArrayList<>();
        for (ITService itService : itServicesList) {
            itServicesDTOList.add(itServiceService.makeAnITServiceDTO(new ITServiceDTO(), itService));
        }
        return itServicesDTOList;
    }
    private ArrayList<ProgramDTO> getProgramDTOList(List<Program> programList){
        ArrayList<ProgramDTO> programDTOList = new ArrayList<>();
        for (Program program : programList) {
            programDTOList.add(programService.makeAProgramDTO(new ProgramDTO(),program));
        }
        return programDTOList;
    }

    private ArrayList<ITService> getITServicesList(List<ITServiceDTO> itServicesDTOList){
        ArrayList<ITService> itServicesList = new ArrayList<>();
        for (ITServiceDTO itServiceDTO : itServicesDTOList) {
            itServicesList.add(itServicesRepository.findById(itServiceDTO.getId())
                    .orElseThrow(EntityNotFoundException::new));
        }
        return itServicesList;
    }
    private ArrayList<Program> getProgramList(List<ProgramDTO> programDTOList){
        ArrayList<Program> programList = new ArrayList<>();
        for (ProgramDTO programDTO : programDTOList) {
            programList.add(programRepository.findById(programDTO.getId())
                    .orElseThrow(EntityNotFoundException::new));
        }
        return programList;
    }
    private List<UploadClass> makeAnUploadClassList(List<FileStorage> fileStorages){
        ArrayList<UploadClass> uploadClasses = new ArrayList<>();
        for (FileStorage fileStorage : fileStorages) {
            uploadClasses.add(makeAnUploadClassFromFileStorage(fileStorage));
        }
        return uploadClasses;
    }
    private UploadClass makeAnUploadClassFromFileStorage(FileStorage fileStorage){
        UploadClass uploadClass = new UploadClass();

        uploadClass.setId(fileStorage.getId());
        uploadClass.setDescription(fileStorage.getDescription());
        uploadClass.setOrderId(fileStorage.getOrderId());
        uploadClass.setFile(fileStorage.getFile());

        return uploadClass;
    }
}