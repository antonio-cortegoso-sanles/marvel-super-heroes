package es.plexus.android.domain_layer

import arrow.core.Either
import arrow.core.left
import arrow.core.right
import com.nhaarman.mockitokotlin2.doReturn
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.whenever
import es.plexus.android.domain_layer.di.domainLayerModule
import es.plexus.android.domain_layer.domain.FailureBo
import es.plexus.android.domain_layer.domain.ResultsBo
import es.plexus.android.domain_layer.domain.SuperHeroesDataBo
import es.plexus.android.domain_layer.usecase.FETCH_SUPER_HEROES_LIST_UC_TAG
import es.plexus.android.domain_layer.usecase.FETCH_SUPER_HERO_DETAIL_UC_TAG
import es.plexus.android.domain_layer.usecase.GET_SUPER_HEROES_LIST_PERSISTED_UC_TAG
import es.plexus.android.domain_layer.usecase.GET_SUPER_HERO_DETAIL_PERSISTED_UC_TAG
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.After
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.koin.core.context.startKoin
import org.koin.core.context.stopKoin
import org.koin.core.inject
import org.koin.core.qualifier.named
import org.koin.dsl.module
import org.koin.test.KoinTest

@ExperimentalCoroutinesApi
class UseCaseTest : KoinTest {

    private val fetchSuperHeroesListDataUc : DomainLayerContract.Presentation.UseCase<Any, Boolean> by inject(
        named(name = FETCH_SUPER_HEROES_LIST_UC_TAG))

    private val fetchSuperHeroDetailUc : DomainLayerContract.Presentation.UseCase<Int, Int> by inject(
        named(name = FETCH_SUPER_HERO_DETAIL_UC_TAG))

    private val getSuperHeroesListDataUc : DomainLayerContract.Presentation.UseCase<Any, SuperHeroesDataBo> by inject(
        named(name = GET_SUPER_HEROES_LIST_PERSISTED_UC_TAG))

    private val getSuperHeroDetailUc : DomainLayerContract.Presentation.UseCase<Int, ResultsBo> by inject(
        named(name = GET_SUPER_HERO_DETAIL_PERSISTED_UC_TAG))

    private val superHeroesRepository : DomainLayerContract.Data.SuperHeroesRepository = mock()

    @Before
    fun setUp() {
        startKoin {
            modules(listOf(domainLayerModule[1], module(override = true) {
                factory(named(name = DomainLayerContract.Data.SUPER_HEROES_REPOSITORY_TAG)) { superHeroesRepository }
            }))
        }
    }

    @After
    fun tearDown() {
        stopKoin()
    }

    @Test
    fun `Fetching Super Heroes Data With Success`() =
        runBlockingTest {
            // given
            whenever(superHeroesRepository.fetchSuperHeroesListData()).doReturn(true.right())
            // when
            val actualResult = fetchSuperHeroesListDataUc.run()
            // then
            Assert.assertTrue(actualResult is Either.Right<Boolean>)
        }

    @Test
    fun `Fetching Super Heroes Data With Failure`() =
        runBlockingTest {
            // given
            whenever(superHeroesRepository.fetchSuperHeroesListData()).doReturn(FailureBo.NoData().left())
            // when
            val actualResult = fetchSuperHeroesListDataUc.run()
            // then
            Assert.assertTrue((actualResult as? Either.Left<FailureBo>)?.a is FailureBo.NoData)
        }

    @Test
    fun `Fetching Super Hero Detail With Success`() =
        runBlockingTest {
            // given
            whenever(superHeroesRepository.fetchSuperHeroDetailData(1)).doReturn(1.right())
            // when
            val actualResult = fetchSuperHeroDetailUc.run(1)
            // then
            Assert.assertTrue(actualResult is Either.Right<Int>)
        }

    @Test
    fun `Fetching Super Hero Detail With Failure`() =
        runBlockingTest {
            // given
            whenever(superHeroesRepository.fetchSuperHeroDetailData(1)).doReturn(FailureBo.NoData().left())
            // when
            val actualResult = fetchSuperHeroDetailUc.run(1)
            // then
            Assert.assertTrue((actualResult as? Either.Left<FailureBo>)?.a is FailureBo.NoData)
        }

    @Test
    fun `Getting Super Heroes Data With Success`() =
        runBlockingTest {
            // given
            whenever(superHeroesRepository.getSuperHeroesListPersistedData()).doReturn(getMockedSuperHeroData().right())
            // when
            val actualResult = getSuperHeroesListDataUc.run()
            // then
            Assert.assertTrue(actualResult is Either.Right<SuperHeroesDataBo>)
        }

    @Test
    fun `Getting Super Hero Detail With Success`() =
        runBlockingTest {
            // given
            whenever(superHeroesRepository.getSuperHeroesDetailPersistedData(1)).doReturn(getMockedSuperHeroDetail().right())
            // when
            val actualResult = getSuperHeroDetailUc.run(1)
            // then
            Assert.assertTrue(actualResult is Either.Right<ResultsBo>)
        }


    private fun getMockedSuperHeroData() : SuperHeroesDataBo = SuperHeroesDataBo(emptyList())

    private fun getMockedSuperHeroDetail() : ResultsBo = ResultsBo(1,"test","test")
}