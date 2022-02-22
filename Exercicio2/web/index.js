function getInputs(filter) {
  return $(filter);
}

function getWrapped(filter) {
  raw = $(filter);
  return $(raw);
}

function extractKeyValueFromInputs(inputs) {
  let keyValue = [];

  inputs.each(function (key, tag) {
    let input = getWrapped('[name="' + tag.name + '"]');
    keyValue.push({
      key: tag.name,
      value: tag.value,
      extra: input.data("extra"),
    });
  });

  return keyValue;
}

function appendError(name, message) {
  let errorHolder = $(".error-" + name);

  errorHolder.html(message);
}

function resetErrors(error_holder) {
  let holders = $(error_holder);

  holders.each(function (key, value) {
    value.innerHTML = "";
  });
}

function fieldsPassValidation() {
  resetErrors("span.error");
  let inputs = getInputs("input");
  let data = extractKeyValueFromInputs(inputs);

  let pass = true;

  for (field in data) {
    if (data[field].value == "") {
      appendError(data[field].key, "Este campo não pode estar vazio.");
      pass = false;
    }
  }

  if (pass && data[0].value != data[1].value) {
    alert("Name e username precisam ser iguais.");
    pass = false;
  }

  return pass;
}

$(function () {
  $("button").on("click", function (event) {
    if (!fieldsPassValidation()) {
      event.preventDefault();
    }
  });
});
