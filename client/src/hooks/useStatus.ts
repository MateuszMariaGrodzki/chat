import { useState } from "react";

type Status = null | "success" | "error" | "pending";

export const useStatus = (initialStatus: Status = null) => useState<null | "success" | "error" | "pending">(initialStatus);
