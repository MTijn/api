package nl.martijnklene.api.application.validator;

import nl.martijnklene.api.application.repository.BlogPostRepository;
import nl.martijnklene.api.infrastructure.annotation.UniqueTitle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class UniqueTitleValidator implements ConstraintValidator<UniqueTitle, String> {
    private BlogPostRepository blogPostRepository;

    @Autowired
    public UniqueTitleValidator(BlogPostRepository blogPostRepository) {
        this.blogPostRepository = blogPostRepository;
    }

    @Override
    public void initialize(UniqueTitle uniqueTitle) {
    }

    @Override
    public boolean isValid(String title, ConstraintValidatorContext constraintValidatorContext) {
        try {
            this.blogPostRepository.findByTitle(title);
        } catch (EmptyResultDataAccessException exception) {
            return true;
        }
        return false;
    }
}
