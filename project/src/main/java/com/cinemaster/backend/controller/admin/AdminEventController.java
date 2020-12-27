package com.cinemaster.backend.controller.admin;

import com.cinemaster.backend.controller.CookieMap;
import com.cinemaster.backend.controller.EventCreationParams;
import com.cinemaster.backend.core.exception.EventsNotCreatedException;
import com.cinemaster.backend.core.exception.ForbiddenException;
import com.cinemaster.backend.core.exception.ShowNotFoundException;
import com.cinemaster.backend.data.dto.*;
import com.cinemaster.backend.data.entity.Room;
import com.cinemaster.backend.data.service.EventService;
import com.cinemaster.backend.data.service.ShowService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "/admin/events")
@CrossOrigin(origins = "http://localhost:4200", allowedHeaders = "*", allowCredentials = "true")
public class AdminEventController {

    @Autowired
    private EventService eventService;

    @Autowired
    private ShowService showService;

    @GetMapping("")
    public ResponseEntity eventList(@CookieValue(value = "sessionid", defaultValue = "") String sessionId) {
        AccountPasswordLessDto accountDto = CookieMap.getInstance().getMap().get(sessionId);
        if (accountDto != null && accountDto instanceof AdminPasswordLessDto) {
            return ResponseEntity.ok(eventService.findAll());
        } else {
            throw new ForbiddenException();
        }
    }

    @PostMapping("")
    public ResponseEntity eventAdd(@RequestBody EventCreationParams eventCreationParams, @CookieValue(value = "sessionid", defaultValue = "") String sessionId) {
        AccountPasswordLessDto accountDto = CookieMap.getInstance().getMap().get(sessionId);
        if (accountDto != null && accountDto instanceof AdminPasswordLessDto) {
            LocalDate index = LocalDate.of(eventCreationParams.getStartDate().getYear(), eventCreationParams.getStartDate().getMonth(), eventCreationParams.getStartDate().getDayOfMonth());
            List<EventDto> droppedEvents = new ArrayList<>();
            while (index.isBefore(eventCreationParams.getEndDate().plusDays(1))) {
                for (LocalTime startTime : eventCreationParams.getStartTimes()) {
                    EventDto eventDto = new EventDto();
                    eventDto.setShow(eventCreationParams.getShow());
                    eventDto.setRoom(eventCreationParams.getRoom());
                    eventDto.setDate(index);
                    eventDto.setStartTime(startTime);
                    ShowDto show = showService.findById(eventCreationParams.getShow().getId()).orElseThrow(() -> new ShowNotFoundException());
                    LocalTime endTime = startTime.plusMinutes(show.getLength());
                    eventDto.setEndTime(endTime);
                    Optional<EventDto> optional = eventService.save(eventDto);
                    if (optional.isPresent()) {
                        droppedEvents.add(optional.get());
                    }
                }
                index = index.plusDays(1);
            }
            if (droppedEvents.isEmpty()) {
                return ResponseEntity.ok("Events created successfully");
            } else {
                StringBuilder stringBuilder = new StringBuilder();
                for (EventDto event : droppedEvents) {
                    stringBuilder.append(event.getDate() + " - " + event.getStartTime() + " - " + event.getEndTime() + "\n");
                }
                throw new EventsNotCreatedException(stringBuilder.toString());
            }
        } else {
            throw new ForbiddenException();
        }
    }


//    // TODO: business logic, how to manage when changing something important (tickets refund?)
//    @PutMapping("")
//    public ResponseEntity showEdit(@RequestBody ShowDto showDto, @CookieValue(value = "sessionid", defaultValue = "") String sessionId) {
//        AccountPasswordLessDto accountDto = CookieMap.getInstance().getMap().get(sessionId);
//        if (accountDto != null && accountDto instanceof AdminPasswordLessDto) {
//            showService.update(showDto);
//            return ResponseEntity.ok(showDto);
//        } else {
//            throw new ForbiddenException();
//        }
//    }
//
//    @DeleteMapping("")
//    public ResponseEntity showDelete(@RequestBody ShowDto showDto, @CookieValue(value = "sessionid", defaultValue = "") String sessionId) {
//        AccountPasswordLessDto accountDto = CookieMap.getInstance().getMap().get(sessionId);
//        if (accountDto != null && accountDto instanceof AdminPasswordLessDto) {
//            showService.delete(showDto);
//            return ResponseEntity.ok("Successfully deleted");
//        } else {
//            throw new ForbiddenException();
//        }
//    }

}
