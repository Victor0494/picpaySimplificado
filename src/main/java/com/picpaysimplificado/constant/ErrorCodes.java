package br.com.livelo.customerb2b.constants;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum ErrorCodes {

	INTERNAL_SERVER_ERROR("Internal server error"),
	INVALID_REQUEST("Invalid request"),
	NOT_FOUND("Not found"),
	FORBIDDEN("Forbidden to execute operation"),
	CLM_EXCEPTION("Error to process request in CLM"),
	UNAUTHORIZED("Unauthorized."),
	INVALID_SECTORS("The company does not have permission for one or more sectors in the payload"),
	INVALID_ROLES("The company does not have permission for one or more roles in the payload."),
	CREATE_PERMISSION_DATA_ACCESS("Error to create permission at data access management"),
	GET_COMPANY_ERROR("The company status can't be false and the type of integration must be %s"),

	WORKLOG_EVENT_SEND_RESET_PASSWORD_SUCCESS("Sucesso na solicitação de reset de senha para o usuário com email: %s, para a empresa de CNPJ: " +
			"%s"),
	WORKLOG_EVENT_SEND_RESET_PASSWORD_ERROR("Erro na solicitação de reset de senha para o usuário com email: %s, para a empresa de CNPJ: %s"),
	WORKLOG_EVENT_EXECUTE_ACTIONS_EMAIL_SUCCESS("Sucesso na solicitação de atualização do cadastro do usuário com email: %s, para a empresa de " +
			"CNPJ: %s"),
	WORKLOG_EVENT_EXECUTE_ACTIONS_EMAIL_ERROR("Erro na solicitação de atualização do cadastro do usuário com email: %s, para a empresa de CNPJ:" +
			" %s"),
	WORKLOG_EVENT_UPDATE_PROFILE_SUCCESS("Sucesso na atualização do cadastro do usuário com email: %s, para a empresa de CNPJ: %s"),
	WORKLOG_EVENT_UPDATE_PROFILE_ERROR("Erro na atualização do cadastro do usuário com email: %s, para a empresa de CNPJ: %s"),
	WORKLOG_EVENT_UPDATED_PASSWORD_SUCCESS("Sucesso na atualização da senha do usuário com email: %s, para a empresa de CNPJ: %s"),
	WORKLOG_EVENT_UPDATED_PASSWORD_ERROR("Erro na atualização da senha do usuário com email: %s, para a empresa de CNPJ: %s"),
	WORKLOG_PARTNER_CODE_SUFIX(" e PARTNER_CODE: %s"),
	WORKLOG_ERROR_PREFIX(". Descrição do erro: "),
	NOTIFICATION_EVENT_GENERATION_ERROR("Erro ao gerar evento de notificação"),

	INVALID_PARTNER_REGISTER("The register of partner: %s, is invalid in CLM or partner-registration."),
	ERROR_FIND_CUSTOMER_SECTORS_WITH_BALANCE("Not found customer sectors"),
	ERROR_FIND_CUSTOMER_CUSTOMERS("Not found customers"),
	NOT_FOUND_MODULE_PERMISSION("Not found modules to check userType"),
	NOT_FOUND_CUSTOMER_SECTORS_PERMISSION("Not found customer sectors permission"),
	INVALID_PAYLOAD_CUSTOMER_SECTORS_PERMISSION("Return of data-access-management dont contains a valid payload"),
	ACTIVATE_COMPANY_STATUS_CHANGE_EXCEPTION("Error to change company status to ACTIVE in CLM"),
	GET_COMPANY_EXCEPTION("Error to get company by CNPJ in CLM"),
	CNPJ_NULL_EXCEPTION("CNPJ can't be null"),

	SCHEDULER_REMOVE_USER_ERROR("Failed to remove an user."),
	INVALID_EVENT_TYPE_TO_SEND_EMAIL("The eventType to send email is invalid."),

	ERROR_USER_NOT_HAS_GROUP("The user: %s has not a company group."),
	ERROR_GROUP_CNPJ_NOT_FOUND("The groupId: %s not has a CNPJ."),

	CUSTOMER_NO_CONTENT("User has no consents for this request"),
	UPDATE_PERMISSION_DATA_ACCESS("Error updating permissions in data-access-management"),
	DELETE_PERMISSION_DATA_ACCESS("Error to delete permissions from data access management"),
	GET_PERMISSION_DATA_ACCESS("Error to get permissions from data-access-management"),

	NOT_FOUND_MIGRATION_SEARCH("No result for CNPJ %s search"),

	CREATE_USER_KEYCLOAK("Error to create user in keycloak."),
	DELETE_USER_KEYCLOAK("Error to delete user in keycloak."),
	GET_ROLES_KEYCLOAK("Error to get roles from keycloak."),
	CREATE_USER_ALREADY_EXISTS_KEYCLOAK("Error to create user in keycloak because username already exists."),
	USER_EMAIL_ALREADY_EXISTS_KEYCLOAK("Error to create user in keycloak because user email already exists."),
	FRAUD_CHECK_IN_QUARENTINE("Fraud check in quarentine"),
	FRAUD_CHECK_IN_QUARENTINE_MESSAGE("User register data has not accepted by fraud check, including in quarentine status"),
	ANTI_FRAUD_RESPONSE_NULL("The antiFraud response is null"),
	RESET_USER_PASSWORD_KEYCLOAK("Error to reset user password in keycloak."),
	GET_ROLE_BY_NAME_FROM_KEYCLOAK("Error to get role by name from keycloak."),
	GROUP_COMPANY_NOT_FOUND("The cnpj: %s, not has a group in keycloak."),
	GROUP_COMPANY_NOT_FOUND_DB("The cnpj: %s, not has a group in database."),
	GROUP_NOT_FOUND("Group was not found in Keycloak"),
	GROUP_NOT_FOUND_DB("Group was not found in database"),
	CREATE_GROUP_KEYCLOAK("Error to create group in keycloak."),
	CREATE_GROUP_DB("Error to create group in database."),
	DIFFERENT_USER_KEYCLOAK("The user returned is different from the parameter passed"),
	USERNAME_EMAIL_DIFFERENT("The username and email are different"),

	INVALID_GROUP_COMPANY("The register of company group: %s, not has required attributes."),
	NEW_USER_NOT_INITIAL_STATUS("The newly created user does not have the initial status."),
	COMPANY_NOT_CONTAIN_USER_APPROVER("Company does not have an approving user"),

	UPDATE_USER_KEYCLOAK("Error to update user in keycloak."),
	INVALID_STATUS_UPDATE("The transition of the status is invalid"),
	USER_NOT_FOUND("User not found."),
	GET_USERS_BY_UUID_KEYCLOAK("Error to get user by UUID from keycloak."),

	CREATE_USER_ALREADY_EXISTS_AND_NOT_DELETED("Error to create user because already exists and is not deleted status.");


	private final String message;

}
