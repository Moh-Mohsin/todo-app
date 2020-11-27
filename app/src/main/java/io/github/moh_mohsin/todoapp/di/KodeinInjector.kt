package io.github.moh_mohsin.todoapp.di

import io.github.moh_mohsin.todoapp.data.repository.TaskRepository
import io.github.moh_mohsin.todoapp.data.repository.impl.TaskRepositoryImpl
import io.github.moh_mohsin.todoapp.data.source.TaskDataSource
import io.github.moh_mohsin.todoapp.data.source.inmemory.InMemoryTaskDataSource
import io.github.moh_mohsin.todoapp.domain.AddTaskUseCase
import io.github.moh_mohsin.todoapp.domain.GetTaskUseCase
import io.github.moh_mohsin.todoapp.domain.GetTasksUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.kodein.di.Kodein
import org.kodein.di.erased.bind
import org.kodein.di.erased.instance
import org.kodein.di.erased.provider
import org.kodein.di.erased.singleton
import kotlin.coroutines.CoroutineContext

@ExperimentalCoroutinesApi
val KodeinInjector = Kodein {

    bind<CoroutineContext>() with provider { Dispatchers.Default }

    /**
     * UseCases
     */

    bind<GetTasksUseCase>() with provider { GetTasksUseCase(instance()) }
    bind<GetTaskUseCase>() with provider { GetTaskUseCase(instance()) }
    bind<AddTaskUseCase>() with provider { AddTaskUseCase(instance()) }

    /**
     * Repositories
     */

    bind<TaskRepository>() with provider { TaskRepositoryImpl(instance()) }

    /**
     * Data Sources
     */

    bind<TaskDataSource>() with singleton { InMemoryTaskDataSource() }
}