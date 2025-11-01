package br.thiago.splitmateapp.domain.usecase

import br.thiago.splitmateapp.domain.model.Split
import br.thiago.splitmateapp.domain.repository.SplitRepository
import com.google.common.truth.Truth.assertThat
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class SplitUseCasesTest {

    private val mockRepo: SplitRepository = mockk(relaxed = true)

    private val addSplitUseCase = AddSplitUseCase(mockRepo)
    private val deleteSplitUseCase = DeleteSplitUseCase(mockRepo)
    private val getSplitsUseCase = GetSplitsUseCase(mockRepo)

    @Test
    fun `AddSplitUseCase should call repository add`() = runTest {
        val split = Split(1, "01/11/2025", 100.0, 2)

        addSplitUseCase(split)

        coVerify(exactly = 1) { mockRepo.add(split) }
    }

    @Test
    fun `DeleteSplitUseCase should call repository delete`() = runTest {
        val split = Split(1, "01/11/2025", 100.0, 2)

        deleteSplitUseCase(split)

        coVerify(exactly = 1) { mockRepo.delete(split) }
    }

    @Test
    fun `GetSplitsUseCase should return list from repository`() = runTest {
        val mockList = listOf(Split(1, "01/11/2025", 100.0, 2))
        coEvery { mockRepo.getAll() } returns mockList

        val result = getSplitsUseCase()

        assertThat(result).isEqualTo(mockList)
        coVerify(exactly = 1) { mockRepo.getAll() }
    }
}
