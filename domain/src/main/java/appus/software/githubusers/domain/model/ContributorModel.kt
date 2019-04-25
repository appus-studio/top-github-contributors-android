package appus.software.githubusers.domain.model

import appus.software.githubusers.domain.model.field.Field
import appus.software.githubusers.domain.model.field.FieldAction
import appus.software.githubusers.domain.model.field.FieldType

/**
 * Created by bogdan.martynov on 4/12/19 2:01 PM. gitHubUsers
 */

data class ContributorModel(
    val total: Int,
    val user: UserModel
): Field {
    override val fieldType: FieldType = FieldType.CONTRIBUTOR
    override var fieldAction: FieldAction = FieldAction.CLICK
}