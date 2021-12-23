import axios from "axios";

export const http = axios.create({
	baseURL:"http://localhost:8080",
	timeout: 10000
})

export const httpBanco = axios.create({
	baseURL:"http://localhost:8081/endereco",
	timeout: 10000,
})
