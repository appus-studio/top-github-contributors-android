package appus.software.githubusers.domain.executor

import java.util.concurrent.Executor

/**
 * Executor implementation can be based on different frameworks or techniques of asynchronous
 * execution, but every implementation will execute the
 * {@link appus.software.live_tv.domain.UseCase} out of the UI thread.
 */

interface ThreadExecutor : Executor