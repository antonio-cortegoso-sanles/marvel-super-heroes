package es.plexus.android.domain_layer

import arrow.core.Either
import es.plexus.android.domain_layer.domain.FailureBo
import es.plexus.android.domain_layer.domain.ResultsBo
import es.plexus.android.domain_layer.domain.SuperHeroesDataBo
import kotlinx.coroutines.*

interface DomainLayerContract {

    interface Presentation {

        interface UseCase<in T, out S> {
            fun invoke(
                scope: CoroutineScope,
                params: T? = null,
                onResult: (Either<FailureBo, S>) -> Unit,
                dispatcherWorker: CoroutineDispatcher = Dispatchers.IO
            ) {
                scope.launch { onResult(withContext(dispatcherWorker) { run(params) }) }
            }

            suspend fun run(params: T? = null): Either<FailureBo, S>
        }
    }

    interface Data {

        companion object {
            const val SUPER_HEROES_REPOSITORY_TAG = "superHeroesRepository"
        }

        interface SuperHeroesRepository {
            suspend fun fetchSuperHeroesListData(): Either<FailureBo, Boolean>
            suspend fun fetchSuperHeroDetailData(id: Int): Either<FailureBo, Int>

            suspend fun saveSuperHeroesListData(data: SuperHeroesDataBo): Either<FailureBo, Boolean>
            suspend fun saveSuperHeroesDetailData(data: ResultsBo): Either<FailureBo, Int>

            suspend fun getSuperHeroesListPersistedData(): Either<FailureBo, SuperHeroesDataBo>
            suspend fun getSuperHeroesDetailPersistedData(id: Int): Either<FailureBo, ResultsBo>

        }

    }
}