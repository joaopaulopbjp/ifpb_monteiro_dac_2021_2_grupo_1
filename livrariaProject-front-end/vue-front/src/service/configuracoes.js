import axios from "axios";

export const http = axios.create({
	baseURL:"http://localhost:8081",
	timeout: 10000,
	headers: {
		"Access-Control-Allow-Origin": "*",
		'Access-Control-Allow-Methods': 'GET, POST, PATCH, PUT, DELETE, OPTIONS',
		'Access-Control-Allow-Headers': 'Origin, Content-Type, X-Auth-Token'
	},
	withCredentials: false
})

export const httpBanco = axios.create({
	baseURL:"http://localhost:8080/endereco",
	timeout: 10000,
})
