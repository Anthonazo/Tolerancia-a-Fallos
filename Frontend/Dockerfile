FROM node:16.14-alpine AS builder
WORKDIR /app
COPY PracticaSistemasDistribuidos/package.json /app
RUN npm install
COPY . /app
WORKDIR /app/PracticaSistemasDistribuidos
RUN npm run build

FROM nginx:1.21.0-alpine
COPY --from=builder /app/PracticaSistemasDistribuidos/dist/prueba-sistemas-distribuidos /usr/share/nginx/html
COPY nginx.conf /etc/nginx/conf.d/default.conf

EXPOSE 80
CMD ["nginx", "-g", "daemon off;"]



