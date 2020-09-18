package cn.lmsite.imghub.controller.request;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class BaseRequest<T> implements Serializable {

    private static final long serialVersionUID = -1340982635778743133L;

    private T requestBody;

}
