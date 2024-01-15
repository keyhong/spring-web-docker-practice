up:
	docker compose -f docker-compose-local.yml up -d

down:
	docker compose -f docker-compose-local.yml down

down-v:
	docker compose -f docker-compose-local.yml down -v	

show-logs:
	docker compose -f docker-compose-local.yml logs

show-logs-postgres:
	docker compose -f docker-compose-local.yml logs postgres

show-logs-spring:
	docker compose -f docker-compose-local.yml logs spring

volume:
	docker volume inspect hello-spring_local_postgres_data	