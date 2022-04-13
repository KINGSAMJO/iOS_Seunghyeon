package co.kr.sopt_seminar_30th.data.mapper

import co.kr.sopt_seminar_30th.data.model.RepositoryDto
import co.kr.sopt_seminar_30th.domain.entity.repository.RepositoryInformation

object RepositoryMapper {
    fun mapperToRepositoryDto(repositoryInformation: RepositoryInformation): RepositoryDto {
        return RepositoryDto(
            repositoryInformation.repositoryName,
            repositoryInformation.repositoryDescription
        )
    }

    fun mapperToRepositoryInformation(repositoryDto: RepositoryDto): RepositoryInformation {
        return RepositoryInformation(
            repositoryDto.repositoryName,
            repositoryDto.repositoryDescription
        )
    }
}