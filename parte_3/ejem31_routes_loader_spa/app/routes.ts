import { type RouteConfig, index, layout, route } from "@react-router/dev/routes";

export default [
  layout("routes/home.tsx", [
    index("routes/book-list.tsx"),
    route("book/:id", "routes/book-detail.tsx"),
  ]),
] satisfies RouteConfig;
