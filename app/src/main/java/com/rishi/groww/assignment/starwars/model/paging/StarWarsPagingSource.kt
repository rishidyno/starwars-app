package com.rishi.groww.assignment.starwars.model.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.rishi.groww.assignment.starwars.model.entity.ResultCharacters
import com.rishi.groww.assignment.starwars.model.network.StarWarsNetworkRepository
import retrofit2.HttpException
import java.io.IOException

const val PAGE_STARTING_INDEX = 1

class StarWarsPagingSource(
    private val starWarsNetworkRepository: StarWarsNetworkRepository,
) : PagingSource<Int, ResultCharacters>() {
    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, ResultCharacters> {

        val pageKey = params.key ?: PAGE_STARTING_INDEX

        return try {

            val data = starWarsNetworkRepository.getAllCharacters(pageKey).getOrNull()!!
            val prevKey =
                if (data.previous == null) null else data.previous[data.previous.length - 1] - '0'
            val nextKey = if (data.next == null) null else data.next[data.next.length - 1] - '0'
            LoadResult.Page(
                data = data.results,
                prevKey = prevKey,
                nextKey = nextKey

            )

        } catch (e: IOException) {
            LoadResult.Error(e)
        } catch (e: HttpException) {
            LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, ResultCharacters>): Int? {
        TODO("Not yet implemented")
    }
}