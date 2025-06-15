package com.controleonibus.aeptransportepublico.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.ResponseEntity;
import com.controleonibus.aeptransportepublico.repository.TripRepository;
import com.controleonibus.aeptransportepublico.dto.TripDto;
import com.controleonibus.aeptransportepublico.entity.Bus;
import com.controleonibus.aeptransportepublico.repository.BusRepository;
import com.controleonibus.aeptransportepublico.repository.UserRepository;

import jakarta.validation.Valid;

import com.controleonibus.aeptransportepublico.repository.ScheduleRepository;
import com.controleonibus.aeptransportepublico.entity.User;
import org.springframework.web.server.ResponseStatusException;
import com.controleonibus.aeptransportepublico.entity.Schedule;
import com.controleonibus.aeptransportepublico.entity.Trip;
import org.springframework.http.HttpStatus;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@RestController
@RequestMapping("/trips")
public class TripController {

        public TripRepository tripRepository;
        public UserRepository userRepository;
        public BusRepository busRepository;
        public ScheduleRepository scheduleRepository;

        public TripController(TripRepository tripRepository,
                        UserRepository userRepository,
                        BusRepository busRepository,
                        ScheduleRepository scheduleRepository) {
                this.tripRepository = tripRepository;
                this.userRepository = userRepository;
                this.busRepository = busRepository;
                this.scheduleRepository = scheduleRepository;
        }

        @GetMapping
        public List<Trip> list() {
                return tripRepository.findAll();
        }

        @GetMapping("/{id}")
        public Trip find(@PathVariable Long id) {
                return tripRepository.findById(id).orElseThrow(
                                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Trip não encontrada"));

        }

        /*
         * @GetMapping("/trips/{busId}")
         * public ResponseEntity<List<Trip>> findByBusId(@PathVariable Long busId) {
         * List<Trip> trips = tripRepository.findByBusId(busId);
         * if (trips.isEmpty()) {
         * return ResponseEntity.notFound().build();
         * }
         * return ResponseEntity.ok(trips);
         * }
         */

        @PostMapping
        public Trip save(@Valid @RequestBody TripDto tripDto) {
                User driver = userRepository.findById(tripDto.driverID())
                                .orElseThrow(() -> new RuntimeException("Driver not found"));

                Bus bus = busRepository.findById(tripDto.busID())
                                .orElseThrow(() -> new RuntimeException("Bus not found"));

                Schedule schedule = scheduleRepository.findById(tripDto.scheduleID())
                                .orElseThrow(() -> new RuntimeException("Schedule not found"));

                Trip newTrip = new Trip(
                                driver,
                                bus,
                                schedule,
                                schedule.getDepartureTime());
                return tripRepository.save(newTrip);
        }

        @PutMapping("/{id}")
        public Trip update(@PathVariable Long id, @RequestBody TripDto tripDto) {
                Trip existingTrip = tripRepository.findById(id)
                                .orElseThrow(() -> new RuntimeException("Trip não encontrada"));

                User driver = userRepository.findById(tripDto.driverID())
                                .orElseThrow(() -> new RuntimeException("Driver Não encontrado"));

                Bus bus = busRepository.findById(tripDto.busID())
                                .orElseThrow(() -> new RuntimeException("Bus não encontrado"));

                Schedule schedule = scheduleRepository.findById(tripDto.scheduleID())
                                .orElseThrow(() -> new RuntimeException("Schedule não encontrada"));

                existingTrip.setDriver(driver);
                existingTrip.setBus(bus);
                existingTrip.setSchedule(schedule);
                existingTrip.setScheduledDepartureTime(schedule.getDepartureTime());

                return tripRepository.save(existingTrip);
        }

        @DeleteMapping("/{id}")
        public ResponseEntity<Void> delete(@PathVariable Long id) {
                Trip existingTrip = tripRepository.findById(id)
                                .orElseThrow(() -> new RuntimeException("Trip não encontrada"));

                tripRepository.delete(existingTrip);
                return ResponseEntity.noContent().build();
        }
}
