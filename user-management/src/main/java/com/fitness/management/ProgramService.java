package com.fitness.management;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ProgramService {
    private Map<String, Program> programs = new HashMap<>();

    public boolean addProgram(Program program) {
        if (programs.containsKey(program.getTitle())) {
            return false;
        }
        programs.put(program.getTitle(), program);
        return true;
    }

    public boolean updateProgram(String title, String duration, String difficulty, String goals, double price, String schedule, List<String> videos, List<String> documents) {
        Program program = programs.get(title);
        if (program == null) {
            return false;
        }
        program.setDuration(duration);
        program.setDifficulty(difficulty);
        program.setGoals(goals);
        program.setPrice(price);
        program.setSchedule(schedule);
        program.setVideos(videos);
        program.setDocuments(documents);
        return true;
    }

    public boolean deleteProgram(String title) {
        return programs.remove(title) != null;
    }

    public Program getProgram(String title) {
        return programs.get(title);
    }
}
