package com.trianglz.test_doubles.common.posts.data.mapper

import com.trianglz.test_doubles.common.posts.data.models.response.PostDataResponseItem
import com.trianglz.test_doubles.common.posts.domain.models.PostDomainModel


fun PostDataResponseItem.toPostDomainModel(): PostDomainModel {
    return PostDomainModel(id, title, body)
}

