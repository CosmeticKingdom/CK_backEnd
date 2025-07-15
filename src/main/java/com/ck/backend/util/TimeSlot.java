package com.ck.backend.util;

import java.time.LocalTime;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public enum TimeSlot {
    SLOT_08_00("08:00"),
    SLOT_08_30("08:30"),
    SLOT_09_00("09:00"),
    SLOT_09_30("09:30"),
    SLOT_10_00("10:00"),
    SLOT_10_30("10:30"),
    SLOT_11_00("11:00"),
    SLOT_11_30("11:30"),
    SLOT_12_00("12:00"),
    SLOT_12_30("12:30"),
    SLOT_13_00("13:00"),
    SLOT_13_30("13:30"),
    SLOT_14_00("14:00"),
    SLOT_14_30("14:30"),
    SLOT_15_00("15:00"),
    SLOT_15_30("15:30"),
    SLOT_16_00("16:00"),
    SLOT_16_30("16:30"),
    SLOT_17_00("17:00"),
    SLOT_17_30("17:30"),
    SLOT_18_00("18:00"),
    SLOT_18_30("18:30"),
    SLOT_19_00("19:00");

    private final String displayTime;
    private final LocalTime localTime;

    TimeSlot(String displayTime) {
        this.displayTime = displayTime;
        this.localTime = LocalTime.parse(displayTime);
    }

    public String getDisplayTime() {
        return displayTime;
    }

    public LocalTime getLocalTime() {
        return localTime;
    }

    public static TimeSlot fromDisplayTime(String displayTime) {
        return Arrays.stream(TimeSlot.values())
                .filter(slot -> slot.getDisplayTime().equals(displayTime))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Invalid time slot: " + displayTime));
    }

    public static List<String> getAllDisplayTimes() {
        return Arrays.stream(TimeSlot.values())
                .map(TimeSlot::getDisplayTime)
                .collect(Collectors.toList());
    }
}
