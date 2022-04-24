package com.wahidabd.mangain.data.sources.remote

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.wahidabd.mangain.data.models.Komik
import com.wahidabd.mangain.data.networks.service.KomikindoService
import com.wahidabd.mangain.utils.Constant
import retrofit2.HttpException
import javax.inject.Inject

class KomikindoPagingSource @Inject constructor(
    private val service: KomikindoService,
    private val komik: Boolean? = null,
    private val manhwa: Boolean? = null,
    private val manhua: Boolean? = null,
    private val daftar: Boolean? = null,
): PagingSource<Int, Komik>() {


    override fun getRefreshKey(state: PagingState<Int, Komik>): Int? =
        state.anchorPosition?.let { pos ->
            state.closestPageToPosition(pos)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(pos)?.nextKey?.minus(1)
        }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Komik> =
        try {
            val position = params.key ?: Constant.STARTING_PAGE_INDEX
            val res = when{
                daftar == true -> {service.daftar(position)}
                manhua == true -> {service.manhua(position)}
                manhwa == true -> {service.manhwa(position)}
                else -> service.komik(position)
            }

            val komik = res.data

            LoadResult.Page(
                data = komik,
                prevKey = if (position == Constant.STARTING_PAGE_INDEX) null else position - 1,
                nextKey = if (komik.isEmpty()) null else position + 1
            )

        }catch (e: Exception){
            LoadResult.Error(e)
        }catch (e: HttpException){
            LoadResult.Error(e)
        }
}