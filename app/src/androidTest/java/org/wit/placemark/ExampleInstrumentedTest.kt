import android.content.Context
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.wit.placemark.models.PlacemarkJSONStore
import org.wit.placemark.models.PlacemarkModel
import org.wit.placemark.helpers.exists

@RunWith(AndroidJUnit4::class)
class PlacemarkJSONStoreTest {
    private lateinit var context: Context
    private lateinit var store: PlacemarkJSONStore

    @Before
    fun setUp() {
        context = ApplicationProvider.getApplicationContext()
        store = PlacemarkJSONStore(context)
    }

    @Test
    fun testFindAll() {
        val newPlacemark = PlacemarkModel(title = "Test Placemark")
        store.create(newPlacemark)

        val placemarks = store.findAll()
        assertTrue(placemarks.isNotEmpty())
    }

    @Test
    fun testCreate() {
        val newPlacemark = PlacemarkModel(title = "Test Placemark")
        store.create(newPlacemark)

        val foundPlacemark = store.findById(newPlacemark.id)
        assertEquals(newPlacemark, foundPlacemark)
    }

    @Test
    fun testFindById() {
        val newPlacemark = PlacemarkModel(title = "Test Placemark")
        store.create(newPlacemark)

        val foundPlacemark = store.findById(newPlacemark.id)
        assertEquals(newPlacemark.id, foundPlacemark?.id)
    }

    @Test
    fun testUpdate() {
        val newPlacemark = PlacemarkModel(title = "Test Placemark")
        store.create(newPlacemark)

        val updatedPlacemark = newPlacemark.copy(title = "Updated Placemark")
        store.update(updatedPlacemark)

        val foundPlacemark = store.findById(updatedPlacemark.id)
        assertEquals("Updated Placemark", foundPlacemark?.title)
    }

    @Test
    fun testDelete() {
        val newPlacemark = PlacemarkModel(title = "Test Placemark")
        store.create(newPlacemark)

        val initialSize = store.findAll().size

        store.delete(newPlacemark)

        val foundPlacemark = store.findById(newPlacemark.id)
        assertNull(foundPlacemark)
        assertTrue(store.findAll().size < initialSize)
    }
}
