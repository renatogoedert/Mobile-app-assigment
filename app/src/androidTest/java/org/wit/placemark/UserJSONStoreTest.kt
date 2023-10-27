import android.content.Context
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.wit.placemark.models.UserJSONStore
import org.wit.placemark.models.UserModel

@RunWith(AndroidJUnit4::class)
class UserJSONStoreTest {
    private lateinit var context: Context
    private lateinit var store: UserJSONStore

    @Before
    fun setUp() {
        context = ApplicationProvider.getApplicationContext()
        store = UserJSONStore(context)
    }

    @Test
    fun testCreate() {
        val newUser = UserModel(123, "TestUser","test@test.com", "testpassword")
        store.create(newUser)

        val foundUser = store.findByEmail(newUser.email)
        assertEquals(newUser, foundUser)
        store.delete(newUser)
    }

    @Test
    fun testFindAll() {
        val newUser = UserModel( 123,"TestUser","test@test.com", "testpassword")
        store.create(newUser)

        val users = store.findAll()
        assertTrue(users.isNotEmpty())
        store.delete(newUser)
    }

    @Test
    fun testFindByEmail() {
        val newUser = UserModel(123, "TestUser","test@test.com", "testpassword")
        store.create(newUser)

        val foundUser = store.findByEmail(newUser.email)
        assertEquals(newUser.email, foundUser?.email)
        store.delete(newUser)
    }

    @Test
    fun testUpdate() {
        val newUser = UserModel(123, "TestUser","test@test.com", "testpassword")
        store.create(newUser)

        val updatedUser = newUser.copy(name = "UpdatedUser", email = "updated@test.com", password = "updatedpassword")
        store.update(updatedUser)

        val foundUser = store.findByEmail(updatedUser.email)
        assertEquals("UpdatedUser", foundUser?.name)
        assertEquals("updated@test.com", foundUser?.email)
        assertEquals("updatedpassword", foundUser?.password)
        store.delete(newUser)
    }
    @Test
    fun testDelete() {
        val newUser = UserModel(123, "TestUser", "test@test.com", "testpassword")
        store.create(newUser)

        val initialSize = store.findAll().size

        store.delete(newUser)

        val foundUser = store.findByEmail(newUser.email)
        assertNull(foundUser)
        assertTrue(store.findAll().size < initialSize)
    }

    @Test
    fun testAuthenticateValidUser() {
        val newUser = UserModel(123,"TestUser", "test@test.com", "testpassword")
        store.create(newUser)

        val isAuthenticated = store.authenticate("test@test.com", "testpassword")
        assertTrue(isAuthenticated)
        store.delete(newUser)
    }

    @Test
    fun testAuthenticateInvalidUser() {
        val newUser = UserModel(123,"TestUser", "test@test.com", "testpassword")
        store.create(newUser)

        val isAuthenticated = store.authenticate("test@test.com", "invalidpassword")
        assertFalse(isAuthenticated)
        store.delete(newUser)
    }

    @Test
    fun testAuthenticateNonexistentUser() {
        val isAuthenticated = store.authenticate("nonexistent@test.com", "password")
        assertFalse(isAuthenticated)

    }

}