package com.issuetracker.test.search;

import com.issuetracker.search.indexing.AnnotationIndexer;
import com.issuetracker.test.search.tools.Person;
import com.issuetracker.test.search.tools.TestHelper;
import org.junit.Test;

import java.util.Map;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * @author: Jiří Holuša
 */
public class IndexEmbeddedAnnotationTest {

    @Test
    public void testIndexEmbeddedAnnotation() {
        AnnotationIndexer indexer = new AnnotationIndexer();
        Person person = TestHelper.createTester();

        indexer.index(person);
        Map<String, String> index = indexer.getIndexAsMap();

        assertTrue(index.containsKey("address.street"));
        assertTrue(index.containsKey("address.city"));

        assertEquals(TestHelper.ADDRESS_STREET, index.get("address.street"));
        assertEquals(TestHelper.ADDRESS_CITY, index.get("address.city"));
    }

    @Test
    public void testIndexEmbeddedAnnotationWithPrefix() {
        AnnotationIndexer indexer = new AnnotationIndexer();
        Person person = TestHelper.createTester();

        indexer.index(person, "prefix.");
        Map<String, String> index = indexer.getIndexAsMap();

        assertTrue(index.containsKey("prefix.address.street"));
        assertTrue(index.containsKey("prefix.address.city"));

        assertEquals(TestHelper.ADDRESS_STREET, index.get("prefix.address.street"));
        assertEquals(TestHelper.ADDRESS_CITY, index.get("prefix.address.city"));
    }
}