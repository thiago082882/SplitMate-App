package br.thiago.splitmateapp.presentation.screens.split_mate.viewmodel



import br.thiago.splitmateapp.domain.model.Split
import br.thiago.splitmateapp.domain.usecase.SplitUseCases
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.*
import org.junit.After
import org.junit.Before
import org.junit.Test
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.Dispatchers

@OptIn(ExperimentalCoroutinesApi::class)
class SplitViewModelTest {

    private val testDispatcher = StandardTestDispatcher()

    private lateinit var viewModel: SplitViewModel
    private val mockUseCases: SplitUseCases = mockk(relaxed = true)

    @Before
    fun setUp() {
        Dispatchers.setMain(testDispatcher)
        viewModel = SplitViewModel(mockUseCases)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun `loadSplits should update state with list from usecase`() = runTest {
        // Arrange
        val mockList = listOf(Split(1, "01/11/2025", 100.0, 2))
        coEvery { mockUseCases.getSplits() } returns mockList

        // Act
        viewModel.loadSplits()
        advanceUntilIdle()

        // Assert
        assertThat(viewModel.splits.value).isEqualTo(mockList)
        coVerify(exactly = 2) { mockUseCases.getSplits() }

    }

    @Test
    fun `addNewSplit should call addSplit and reload list`() = runTest {
        val newSplit = Split(2, "02/11/2025", 200.0, 3)
        coEvery { mockUseCases.getSplits() } returns listOf(newSplit)

        // Act
        viewModel.addNewSplit(newSplit)
        advanceUntilIdle()

        // Assert
        coVerify { mockUseCases.addSplit(newSplit) }
        assertThat(viewModel.splits.value).contains(newSplit)
    }

    @Test
    fun `removeSplit should call deleteSplit and reload list`() = runTest {
        val split = Split(3, "03/11/2025", 300.0, 4)
        coEvery { mockUseCases.getSplits() } returns emptyList()

        // Act
        viewModel.removeSplit(split)
        advanceUntilIdle()

        // Assert
        coVerify { mockUseCases.deleteSplit(split) }
        assertThat(viewModel.splits.value).isEmpty()
    }
}
