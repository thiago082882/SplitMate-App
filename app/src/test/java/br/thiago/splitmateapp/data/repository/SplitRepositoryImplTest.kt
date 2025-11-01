package br.thiago.splitmateapp.data.repository

import br.thiago.splitmateapp.data.local.SplitDao
import br.thiago.splitmateapp.data.local.SplitEntity
import br.thiago.splitmateapp.data.mapper.toDomain
import br.thiago.splitmateapp.data.mapper.toEntity
import br.thiago.splitmateapp.domain.model.Split
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test
import com.google.common.truth.Truth.assertThat

@OptIn(ExperimentalCoroutinesApi::class)
class SplitRepositoryImplTest {

    private lateinit var dao: SplitDao
    private lateinit var repository: SplitRepositoryImpl

    @Before
    fun setUp() {
        dao = mockk(relaxed = true)
        repository = SplitRepositoryImpl(dao)
    }

    @Test
    fun `getAll should return list of splits mapped from entity`() = runTest {
        // Arrange
        val entityList = listOf(
            SplitEntity(1, "01/11/2025", 100.0, 2),
            SplitEntity(2, "02/11/2025", 200.0, 3)
        )
        coEvery { dao.getAllSplits() } returns entityList

        // Act
        val result = repository.getAll()

        // Assert
        assertThat(result).isEqualTo(entityList.map { it.toDomain() })
        coVerify(exactly = 1) { dao.getAllSplits() }
    }

    @Test
    fun `add should call dao insertSplit with mapped entity`() = runTest {
        // Arrange
        val split = Split(1, "01/11/2025", 100.0, 2)

        // Act
        repository.add(split)

        // Assert
        coVerify(exactly = 1) { dao.insertSplit(split.toEntity()) }
    }

    @Test
    fun `delete should call dao deleteSplit with mapped entity`() = runTest {
        // Arrange
        val split = Split(1, "01/11/2025", 100.0, 2)

        // Act
        repository.delete(split)

        // Assert
        coVerify(exactly = 1) { dao.deleteSplit(split.toEntity()) }
    }
}
