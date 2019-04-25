package appus.software.githubusers.domain.model.field

interface Field {
    val fieldType: FieldType
    var fieldAction: FieldAction
}