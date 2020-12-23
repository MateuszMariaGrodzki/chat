import { useState } from "react";

export const useStatus = () => useState<null | "success" | "error" | "pending">(null);
