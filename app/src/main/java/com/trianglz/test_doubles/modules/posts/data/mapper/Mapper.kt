package com.trianglz.test_doubles.modules.posts.data.mapper

import com.trianglz.test_doubles.modules.posts.data.models.response.PostDataResponseItem
import com.trianglz.test_doubles.modules.posts.domain.models.PostDomainModel


fun PostDataResponseItem.toPostDomainModel(): PostDomainModel {
    return PostDomainModel(id, title, body)
}

