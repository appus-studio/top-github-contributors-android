package appus.software.githubusers.data.executors

import appus.software.githubusers.domain.executor.ThreadExecutor
import io.reactivex.annotations.NonNull
import java.util.concurrent.*



class JobExecutor: ThreadExecutor {

    private val threadPoolExecutor = ThreadPoolExecutor(3, 5, 10, TimeUnit.SECONDS,
            LinkedBlockingQueue<Any>() as BlockingQueue<Runnable>,
        JobThreadFactory()
    )

    override fun execute(@NonNull runnable: Runnable) {
        this.threadPoolExecutor.execute(runnable)
    }

    private class JobThreadFactory : ThreadFactory {
        private var counter = 0

        override fun newThread(@NonNull runnable: Runnable): Thread {
            return Thread(runnable, "android_" + counter++)
        }
    }
}

