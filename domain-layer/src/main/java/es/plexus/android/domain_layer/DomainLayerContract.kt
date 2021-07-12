package es.plexus.android.domain_layer

import arrow.core.Either
import es.plexus.android.domain_layer.domain.*
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect

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

        interface FlowUseCase<in T, out S> {
            var job: Job
            fun invoke(
                scope: CoroutineScope,
                params: T? = null,
                onResult: (Either<FailureBo, S>) -> Unit,
                dispatcherWorker: CoroutineDispatcher = Dispatchers.IO
            ) {
                job = scope.launch {
                    withContext(dispatcherWorker) {
                        run(params)
                    }.collect { result ->
                        onResult(result)
                    }
                }
            }

            fun cancel() {
                job.cancel()
            }

            suspend fun run(params: T? = null): Flow<Either<FailureBo, S>>
        }

    }

    interface Data {

        companion object {
            const val SUPER_HEROES_REPOSITORY_TAG = "superHeroesRepository"
        }

        interface SuperHeroesRepository {
            suspend fun fetchSuperHeroesListData(): Either<FailureBo, Boolean>
            suspend fun fetchSuperHeroDetailData(id: Int): Either<FailureBo, Boolean>

            suspend fun saveSuperHeroesListData(data : SuperHeroesDataBo): Either<FailureBo, Boolean>
            suspend fun saveSuperHeroesDetailData(data : SuperHeroesDataBo): Either<FailureBo, Boolean>

            suspend fun getSuperHeroesListPersistedData(): Either<FailureBo, SuperHeroesDataBo>
            suspend fun getSuperHeroesDetailPersistedData(id: Int): Either<FailureBo, SuperHeroesDataBo>

        }

    }
}