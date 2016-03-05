package com.bls.patronage.resources;

import com.bls.patronage.api.FlashcardRepresentation;
import com.bls.patronage.db.dao.FlashcardDAO;
import com.bls.patronage.db.model.Flashcard;
import io.dropwizard.jersey.params.UUIDParam;

import javax.validation.Valid;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.UUID;

@Path("flashcards/{flashcardId}")
@Produces(MediaType.APPLICATION_JSON)
public class FlashcardResource {
    private final FlashcardDAO flashcardDAO;

    public FlashcardResource(FlashcardDAO flashcardDAO) {
        this.flashcardDAO = flashcardDAO;
    }

    @GET
    public Flashcard getFlashcard(@PathParam("flashcardId") UUIDParam flashcardId) {
        return flashcardDAO.getFlashcardById(flashcardId.get());
    }

    @DELETE
    public void deleteFlashcard(@PathParam("flashcardId") UUIDParam flashcardId) {
        flashcardDAO.deleteFlashcard(flashcardDAO.getFlashcardById(flashcardId.get()).getId());
    }

    @PUT
    public void updateFlashcard(@PathParam("flashcardId") UUID flashcardId,
                                @Valid FlashcardRepresentation flashcard) {
        Flashcard updatedFlashcard = flashcardDAO.getFlashcardById(flashcardId);
        updatedFlashcard.setQuestion(flashcard.getQuestion());
        updatedFlashcard.setAnswer(flashcard.getAnswer());
        flashcardDAO.updateFlashcard(updatedFlashcard);
    }
}
