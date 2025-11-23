package com.example.chart_backend.service;

import com.example.chart_backend.dto.request.CommandRequest;
import com.example.chart_backend.dto.response.CommandResponse;
import com.example.chart_backend.entity.Command;
import com.example.chart_backend.entity.Topic;
import com.example.chart_backend.repository.CommandRepository;
import com.example.chart_backend.repository.TopicRepository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CommandService {

    private final CommandRepository commandRepository;
    private final TopicRepository topicRepository;

    private CommandResponse mapToResponse(Command cmd) {
        Topic topicDto = new Topic();
        topicDto.setId(cmd.getTopic().getId());
        topicDto.setTopicName(cmd.getTopic().getTopicName());

        CommandResponse res = new CommandResponse();
        res.setId(cmd.getId());
        res.setTopic(topicDto); // gán object
        res.setFunc(cmd.getFunc());
        res.setActions(cmd.getActions());
        res.setProcessed(cmd.getProcessed());
        return res;
    }

    public CommandResponse create(CommandRequest request) {
        Topic topic = topicRepository.findById(request.getTopicId())
                .orElseThrow(() -> new RuntimeException("Topic not found"));

        Command cmd = Command.builder()
                .topic(topic)
                .func(request.getFunc())
                .actions(request.getActions())
                .processed(request.getProcessed())
                .build();

        return mapToResponse(commandRepository.save(cmd));
    }

    public List<CommandResponse> getAll() {
        return commandRepository.findAll().stream()
                .map(this::mapToResponse)
                .toList();
    }

    public CommandResponse getById(Long id) {
        Command cmd = commandRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Command not found"));
        return mapToResponse(cmd);
    }

    public CommandResponse update(Long id, CommandRequest request) {

        Command cmd = commandRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Command not found"));

        if (request.getTopicId() != null) {
            Topic topic = topicRepository.findById(request.getTopicId())
                    .orElseThrow(() -> new RuntimeException("Topic not found"));
            cmd.setTopic(topic);
        }

        cmd.setFunc(request.getFunc());
        cmd.setActions(request.getActions());
        cmd.setProcessed(request.getProcessed());

        return mapToResponse(commandRepository.save(cmd));
    }

    public void delete(Long id) {
        if (!commandRepository.existsById(id))
            throw new RuntimeException("Command not found");
        commandRepository.deleteById(id);
    }
}
