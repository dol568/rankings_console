package org.mwo.agh.edu.model;

import java.io.Serializable;
import java.time.LocalDate;

public record Activity(LocalDate date, String description, double duration) implements Serializable {
}
