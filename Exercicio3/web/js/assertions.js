function assertNonEmpty(value) {
  if (value === "") {
    throw new Error("Este campo não pode ser vazio");
  }
}

function assertEmailRegex(value) {
  if (!REGEX_EMAIL.test(value)) {
    throw new Error("Este campo deve ser um email válido");
  }
}

function assertNameRegex(value) {
  if (!REGEX_NAME.test(value)) {
    throw new Error(
      "Este campo pode conter apenas espaços e letras, com ou sem acento"
    );
  }
}
