package nl.martijnklene.api.domain.aggregate;

import org.junit.Assert;
import org.junit.Test;

import java.util.UUID;

public class BlogAggregateTest
{
    private final UUID id = UUID.randomUUID();
    private final String title = "test";

    private final BlogAggregate blogAggregate = new BlogAggregate(id, title);

    @Test
    public void testGettingId() {
        Assert.assertSame(id, blogAggregate.getId());
    }

    @Test
    public void testGettingTitle() {
        Assert.assertSame(title, blogAggregate.getTitle());
    }
}
