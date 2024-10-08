package app.te.architecture.data.remote

import androidx.paging.PagingSource
import androidx.paging.PagingState


abstract class AbstractPagingSource<U : Any> {
  open val STARTING_PAGE_INDEX = 1
  open var nextPage: String? = null

  fun getPagingSource(): PagingSource<Int, U> {
    return object : PagingSource<Int, U>() {
      override suspend fun load(params: LoadParams<Int>): LoadResult<Int, U> {
        val position = params.key ?: STARTING_PAGE_INDEX
        return try {
          val response = fetchData(params.loadSize, position)
          LoadResult.Page(
            data = response,
            prevKey = if (position == STARTING_PAGE_INDEX) null else position - 1,
            nextKey = if (!hasNextPages()) null else position + 1
          )

        } catch (exception: Throwable) {
          LoadResult.Error<Int, U>(exception)
        }
      }

      override fun getRefreshKey(state: PagingState<Int, U>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
          val anchorPage = state.closestPageToPosition(anchorPosition)
          anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }
      }
    }
  }

  abstract suspend fun fetchData(pageSize: Int = 10, PageIndex: Int): List<U>

  open fun hasNextPages() = true
}
