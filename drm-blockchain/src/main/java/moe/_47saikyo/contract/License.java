package moe._47saikyo.contract;

import java.math.BigInteger;
import java.util.Arrays;
import org.web3j.abi.FunctionEncoder;
import org.web3j.abi.TypeReference;
import org.web3j.abi.datatypes.Function;
import org.web3j.abi.datatypes.Type;
import org.web3j.abi.datatypes.Utf8String;
import org.web3j.crypto.Credentials;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.RemoteCall;
import org.web3j.protocol.core.RemoteFunctionCall;
import org.web3j.tx.Contract;
import org.web3j.tx.TransactionManager;
import org.web3j.tx.gas.ContractGasProvider;

/**
 * <p>Auto generated code.
 * <p><strong>Do not modify!</strong>
 * <p>Please use the <a href="https://docs.web3j.io/command_line.html">web3j command line tools</a>,
 * or the org.web3j.codegen.SolidityFunctionWrapperGenerator in the 
 * <a href="https://github.com/web3j/web3j/tree/master/codegen">codegen module</a> to update.
 *
 * <p>Generated with web3j version 1.5.2.
 */
@SuppressWarnings("rawtypes")
public class License extends Contract {
    public static final String BINARY = "608060405234801561001057600080fd5b50604051610a0f380380610a0f83398101604081905261002f916100c9565b600061003b848261023e565b5060018054600160a01b6001600160a81b03199091163360ff60a01b19161717600160a81b600160e81b031916600160a81b63ffffffff9485160263ffffffff60c81b191617600160c81b9290931691909102919091179055506102fd565b634e487b7160e01b600052604160045260246000fd5b805163ffffffff811681146100c457600080fd5b919050565b6000806000606084860312156100de57600080fd5b83516001600160401b03808211156100f557600080fd5b818601915086601f83011261010957600080fd5b81518181111561011b5761011b61009a565b604051601f8201601f19908116603f011681019083821181831017156101435761014361009a565b8160405282815260209350898484870101111561015f57600080fd5b600091505b828210156101815784820184015181830185015290830190610164565b600084848301015280975050505061019a8187016100b0565b935050506101aa604085016100b0565b90509250925092565b600181811c908216806101c757607f821691505b6020821081036101e757634e487b7160e01b600052602260045260246000fd5b50919050565b601f821115610239576000816000526020600020601f850160051c810160208610156102165750805b601f850160051c820191505b8181101561023557828155600101610222565b5050505b505050565b81516001600160401b038111156102575761025761009a565b61026b8161026584546101b3565b846101ed565b602080601f8311600181146102a057600084156102885750858301515b600019600386901b1c1916600185901b178555610235565b600085815260208120601f198616915b828110156102cf578886015182559484019460019091019084016102b0565b50858210156102ed5787850151600019600388901b60f8161c191681555b5050505050600190811b01905550565b6107038061030c6000396000f3fe608060405234801561001057600080fd5b506004361061002b5760003560e01c8063bc8018b114610030575b600080fd5b61003861004e565b604051610045919061043d565b60405180910390f35b600154606090600090610069906001600160a01b0316610115565b600154600160a01b900460ff1661009d576040518060400160405280600581526020016466616c736560d81b8152506100bb565b604051806040016040528060048152602001637472756560e01b8152505b6001546100d490600160a81b900463ffffffff166102f6565b6001546100ed90600160c81b900463ffffffff166102f6565b60405160200161010195949392919061048c565b604051602081830303815290604052905090565b604080518082018252601081526f181899199a1a9b1b9c1cb0b131b232b360811b60208201528151602a80825260608281019094526001600160a01b0385169291600091602082018180368337019050509050600360fc1b816000815181106101805761018061060a565b60200101906001600160f81b031916908160001a905350600f60fb1b816001815181106101af576101af61060a565b60200101906001600160f81b031916908160001a90535060005b60148110156102ed57826004856101e184600c610636565b602081106101f1576101f161060a565b1a60f81b6001600160f81b031916901c60f81c60ff16815181106102175761021761060a565b01602001516001600160f81b0319168261023283600261064f565b61023d906002610636565b8151811061024d5761024d61060a565b60200101906001600160f81b031916908160001a905350828461027183600c610636565b602081106102815761028161060a565b825191901a600f169081106102985761029861060a565b01602001516001600160f81b031916826102b383600261064f565b6102be906003610636565b815181106102ce576102ce61060a565b60200101906001600160f81b031916908160001a9053506001016101c9565b50949350505050565b60608160000361031d5750506040805180820190915260018152600360fc1b602082015290565b8160005b8115610347578061033181610666565b91506103409050600a8361067f565b9150610321565b60008167ffffffffffffffff811115610362576103626105f4565b6040519080825280601f01601f19166020018201604052801561038c576020820181803683370190505b509050815b85156102ed576103a26001826106a1565b905060006103b1600a8861067f565b6103bc90600a61064f565b6103c690886106a1565b6103d19060306106b4565b905060008160f81b9050808484815181106103ee576103ee61060a565b60200101906001600160f81b031916908160001a905350610410600a8961067f565b97505050610391565b60005b8381101561043457818101518382015260200161041c565b50506000910152565b602081526000825180602084015261045c816040850160208701610419565b601f01601f19169190910160400192915050565b60008151610482818560208601610419565b9290920192915050565b6d3d913934b3b43a2730b6b2911d1160911b815285546000908190600181811c90808316806104bc57607f831692505b602080841082036104db57634e487b7160e01b86526022600452602486fd5b8180156104ef576001811461050a5761053d565b60ff198616600e8a0152600e85151586028a0101965061053d565b60008e81526020902060005b868110156105325781548b8201600e0152908501908301610516565b5050600e858a010196505b5050505050506105e86105db6105d56105bb6105b561059c61059661057c610576896a11161137bbb732b9111d1160a91b8152600b0190565b8f610470565b6d11161130bb30b4b630b13632911d60911b8152600e0190565b8c610470565b6c161134b9b9bab2aa34b6b2911d60991b8152600d0190565b89610470565b6d161132bc3834b932aa34b6b2911d60911b8152600e0190565b86610470565b607d60f81b815260010190565b98975050505050505050565b634e487b7160e01b600052604160045260246000fd5b634e487b7160e01b600052603260045260246000fd5b634e487b7160e01b600052601160045260246000fd5b8082018082111561064957610649610620565b92915050565b808202811582820484141761064957610649610620565b60006001820161067857610678610620565b5060010190565b60008261069c57634e487b7160e01b600052601260045260246000fd5b500490565b8181038181111561064957610649610620565b60ff81811683821601908111156106495761064961062056fea26469706673582212201527401a81a512061760f467235685fc135b38b192028395638d3a7c4a26190d64736f6c63430008190033";

    public static final String FUNC_SERIALIZE = "serialize";

    @Deprecated
    protected License(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    protected License(String contractAddress, Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
        super(BINARY, contractAddress, web3j, credentials, contractGasProvider);
    }

    @Deprecated
    protected License(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    protected License(String contractAddress, Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        super(BINARY, contractAddress, web3j, transactionManager, contractGasProvider);
    }

    public RemoteFunctionCall<String> serialize() {
        final Function function = new Function(FUNC_SERIALIZE, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Utf8String>() {}));
        return executeRemoteCallSingleValueReturn(function, String.class);
    }

    @Deprecated
    public static License load(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        return new License(contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    @Deprecated
    public static License load(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        return new License(contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    public static License load(String contractAddress, Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
        return new License(contractAddress, web3j, credentials, contractGasProvider);
    }

    public static License load(String contractAddress, Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        return new License(contractAddress, web3j, transactionManager, contractGasProvider);
    }

    public static RemoteCall<License> deploy(Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider, String _rightName, BigInteger _issueTime, BigInteger _expireTime) {
        String encodedConstructor = FunctionEncoder.encodeConstructor(Arrays.<Type>asList(new org.web3j.abi.datatypes.Utf8String(_rightName), 
                new org.web3j.abi.datatypes.generated.Uint32(_issueTime), 
                new org.web3j.abi.datatypes.generated.Uint32(_expireTime)));
        return deployRemoteCall(License.class, web3j, credentials, contractGasProvider, BINARY, encodedConstructor);
    }

    public static RemoteCall<License> deploy(Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider, String _rightName, BigInteger _issueTime, BigInteger _expireTime) {
        String encodedConstructor = FunctionEncoder.encodeConstructor(Arrays.<Type>asList(new org.web3j.abi.datatypes.Utf8String(_rightName), 
                new org.web3j.abi.datatypes.generated.Uint32(_issueTime), 
                new org.web3j.abi.datatypes.generated.Uint32(_expireTime)));
        return deployRemoteCall(License.class, web3j, transactionManager, contractGasProvider, BINARY, encodedConstructor);
    }

    @Deprecated
    public static RemoteCall<License> deploy(Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit, String _rightName, BigInteger _issueTime, BigInteger _expireTime) {
        String encodedConstructor = FunctionEncoder.encodeConstructor(Arrays.<Type>asList(new org.web3j.abi.datatypes.Utf8String(_rightName), 
                new org.web3j.abi.datatypes.generated.Uint32(_issueTime), 
                new org.web3j.abi.datatypes.generated.Uint32(_expireTime)));
        return deployRemoteCall(License.class, web3j, credentials, gasPrice, gasLimit, BINARY, encodedConstructor);
    }

    @Deprecated
    public static RemoteCall<License> deploy(Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit, String _rightName, BigInteger _issueTime, BigInteger _expireTime) {
        String encodedConstructor = FunctionEncoder.encodeConstructor(Arrays.<Type>asList(new org.web3j.abi.datatypes.Utf8String(_rightName), 
                new org.web3j.abi.datatypes.generated.Uint32(_issueTime), 
                new org.web3j.abi.datatypes.generated.Uint32(_expireTime)));
        return deployRemoteCall(License.class, web3j, transactionManager, gasPrice, gasLimit, BINARY, encodedConstructor);
    }
}
