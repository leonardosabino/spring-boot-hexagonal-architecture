package com.hexagonal.template.transportLayer.http.sqs;

import com.hexagonal.template.interactor.sqs.MessageUseCase;
import com.hexagonal.template.transportLayer.openapi.model.MessageDTO;
import com.hexagonal.template.transportLayer.openapi.api.SqsApi;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(
    path = "sqs"
)
public class ExampleSqsController implements SqsApi {

  @Autowired
  private MessageUseCase messageUseCase;

  public ResponseEntity<List<MessageDTO>> sendMessageSqs(MessageDTO messageDTO) {
    var outgoingSqs = SqsMapper.INSTANCE.messageDTOToOutgoingSqs(messageDTO);
    messageUseCase.send(outgoingSqs);
    return ResponseEntity.accepted().body(SqsMapper.INSTANCE.OutgoingSqsToMessageDTO(List.of(outgoingSqs)));
  }
}