package com.s4n.restaurant.repository.impl;

import com.s4n.restaurant.domain.Coordinate;
import com.s4n.restaurant.domain.Dron;
import com.s4n.restaurant.repository.IDeliveryRoutesRepository;
import com.s4n.restaurant.utils.AppConstants;
import com.s4n.restaurant.utils.FileUtils;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class TextFileRoutesRepository implements IDeliveryRoutesRepository {
    private final String folder = FileUtils.getProperty("instructions-folder");
    /**
     * Extract instructions from file return a String list with instructions
     * @param dron
     * @return
     */
    @Override
    public List<String> getDeliveryInstructions(Dron dron) throws IOException {

        String dronName = AppConstants.IN_FILE_PREFIX + String.format("%02d", dron.getId());
        List<String> instructionsList = new ArrayList<>();
        if (FileUtils.validateFileName(AppConstants.INSTRUCTIONS_FOLDER_REGEX,dronName)){
            String fileName = folder + dronName +  AppConstants.FILE_EXTENSION;
            try (Stream<String> stream = Files.lines(Paths.get(fileName))) {
                instructionsList = stream
                        .map(String::toUpperCase)
                        .limit(Integer.parseInt(Objects.requireNonNull(FileUtils.getProperty("max-deliveries"))))
                        .collect(Collectors.toList());
            }
        }
        return instructionsList;
    }

    @Override
    public void writeDeliveryReport(Dron dron) throws IOException {
        String dronName = AppConstants.OUT_FILE_PREFIX + String.format("%02d", dron.getId());
        String content = AppConstants.FIRST_LINE_REPORT_FILE;
        String fileName = folder + dronName +  AppConstants.FILE_EXTENSION;
        content += dron.getDeliveryList().stream()
                .map(Coordinate::toString)
                .collect(Collectors.joining("\n"));
        Files.write(Paths.get(fileName), content.getBytes());
    }
}
