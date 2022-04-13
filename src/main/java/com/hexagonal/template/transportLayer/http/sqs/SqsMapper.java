package com.hexagonal.template.transportLayer.http.sqs;

import com.hexagonal.template.entity.sqs.IncomingSqs;
import com.hexagonal.template.entity.sqs.OutgoingSqs;
import com.hexagonal.template.transportLayer.openapi.model.MessageDTO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface SqsMapper {
    SqsMapper INSTANCE = Mappers.getMapper(SqsMapper.class);

    OutgoingSqs messageDTOToOutgoingSqs(MessageDTO messageDTO);

    List<MessageDTO> OutgoingSqsToMessageDTO(List<OutgoingSqs> outgoingSqs);

    IncomingSqs messageDTOToIncomingSqs(MessageDTO messageDTO);
}

